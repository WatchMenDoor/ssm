package com.bjsxt.ego.manager.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResults;
import com.bjsxt.ego.manager.service.ManagerContentService;
import com.bjsxt.ego.rpc.pojo.TbContent;
import com.bjsxt.ego.rpc.service.TbContentService;
@Service
public class ManagerContentServiceImpl implements ManagerContentService{
	
	
	@Autowired
	private TbContentService tbContentServiceProxy;

	@Override
	public PageResults<TbContent> loadContentListService(Long cid, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return tbContentServiceProxy.loadTbContentListService(cid, page, rows);
	}

	
	//商品的新增
	@Override
	public EgoResult saveContent(TbContent tbContent) {
		// TODO Auto-generated method stub
		Date date = new Date();
		tbContent.setCreated(date);
		tbContent.setUpdated(date);
		return tbContentServiceProxy.saveTbContentService(tbContent);
	}


	//商品的删除
	@Override
	public EgoResult deleteContent(String ids) {
		// TODO Auto-generated method stub
		String[] idss = ids.split(",");
		
		List<Long> list = new ArrayList<Long>();
		for (String id : idss) {
			list.add(Long.parseLong(id));
			
		}
		return tbContentServiceProxy.deleteTbContentService(list);
	}

	//商品的更新
	@Override
	public EgoResult updateContent(TbContent tbContent) {
		// TODO Auto-generated method stub
		
		Date date = new Date();
		tbContent.setUpdated(date);
		return tbContentServiceProxy.updateTbContentService(tbContent);
	}
	
	
	
	
	
	
	
	

}
