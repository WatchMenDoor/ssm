package com.bjsxt.ego.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bjsxt.ego.beans.BigPicture;
import com.bjsxt.ego.beans.JsonUtils;
import com.bjsxt.ego.portal.service.PortalContentService;
import com.bjsxt.ego.rpc.pojo.TbContent;
import com.bjsxt.ego.rpc.service.TbContentService;

import redis.clients.jedis.JedisCluster;

@Service
public class PortalContentServiceImpl implements PortalContentService{

	@Value("${CONTENT_PICTURE}")
	private String contentPicturekey;
	
	//注入redis 集群对象
	@Autowired
	private JedisCluster cluster;
	
	
	
	@Autowired
	private TbContentService tbContentServiceProxy;
	
	@Override
	public String loadContentListByCidService(Long cid) {
		
		
		// TODO Auto-generated method stub
		String jsonStr = cluster.get(contentPicturekey);
		
		if(!StringUtils.isEmpty(jsonStr)){
			return jsonStr;
		}
		
		
		List<TbContent> list = tbContentServiceProxy.loadTbContentByCidService(cid);
		//封装符合前台数据格式
		List<BigPicture> bigList = new ArrayList<>();
		for (TbContent content : list) {
			BigPicture pic = new BigPicture();
			pic.setSrcb(content.getPic());
			pic.setHeight(240);
			pic.setAlt(content.getTitle());
			pic.setWidth(670);
			pic.setSrc(content.getPic2());
			pic.setWidthb(550);
			pic.setHref(content.getUrl());
			pic.setHeightb(240);
			
			bigList.add(pic);
			
		}
		//
		String objectToJson = JsonUtils.objectToJson(bigList);
		
		//保存到redis
		cluster.set(contentPicturekey, objectToJson);
		cluster.expire(contentPicturekey, 86400);
		
		
		return objectToJson;
	}

	
	
	
}
