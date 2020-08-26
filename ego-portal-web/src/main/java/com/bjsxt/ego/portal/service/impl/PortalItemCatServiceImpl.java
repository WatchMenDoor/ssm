package com.bjsxt.ego.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bjsxt.ego.beans.CatNode;
import com.bjsxt.ego.beans.CatResult;
import com.bjsxt.ego.beans.JsonUtils;
import com.bjsxt.ego.portal.service.PortalItemCatService;
import com.bjsxt.ego.rpc.pojo.TbItemCat;
import com.bjsxt.ego.rpc.service.ItemCatService;
import com.bjsxt.ego.rpc.service.ItemService;

import redis.clients.jedis.JedisCluster;

@Service
public class PortalItemCatServiceImpl implements PortalItemCatService {

	
	@Value("${ITEM_CAT}")
	private String itemCatkey;
	
	//注入redis 集群对象
	@Autowired
	private JedisCluster cluster;
	
	@Autowired
	private ItemCatService itemCatServiceProxy;
	@Override
	public String loadItemCatService() {
		// TODO Auto-generated method stub
		//先查redis
		String jsonStr = cluster.get(itemCatkey);
		if(!StringUtils.isEmpty(jsonStr)){
			return jsonStr;
			
		}
		List<TbItemCat> list = itemCatServiceProxy.loadItemCatService();
		//将list转换为前端规范的参数
		CatResult result = new CatResult();
		
		//递归遍历list
		List<?> data = getChildren(0L,list);
		result.setData(data);
		
		//转换为json格式
		String json = JsonUtils.objectToJson(result);
		
		//将json缓存到redis数据库
		cluster.set(itemCatkey, json);
		return json;
	}
	
	
	private List<?> getChildren(Long parentId, List<TbItemCat> itemCats) {
		// 盛放指定分类下的所有子分类信息
		List resultList = new ArrayList();

		for (TbItemCat itemCat : itemCats) {

			if (itemCat.getParentId().equals(parentId)) {
				if (itemCat.getIsParent()) {
					// 如果itemCat代表一级分类或者二级分类

					CatNode catNode = new CatNode();

					if (itemCat.getParentId().longValue() == 0) {
						// 如果是一级分类 "<a href='/products/1.html'>图书、音像、电子书刊</a>",
						catNode.setName(
								"<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
					} else {
						// 如果是二级分类 "电子书刊",
						catNode.setName(itemCat.getName());
					}
					// "/products/2.html",
					catNode.setUrl("/products/" + itemCat.getId() + ".html");
					catNode.setList(getChildren(itemCat.getId(), itemCats));
					// 将节点添加到list集合中
					resultList.add(catNode);
				} else {
					// 如果itemCat表示三级分类 "/products/3.html|电子书",
					resultList.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName());
				}
			}
		}
		return resultList;
	}
	
	
	
	

}
