package com.bjsxt.ego.search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.bjsxt.ego.rpc.service.ItemDescService;
import com.bjsxt.ego.search.service.SearchItemDescService;

public class SearchItemDescServiceImpl implements SearchItemDescService{
	
	
	@Autowired
	private ItemDescService itemDescServiceProxy;

	@Override
	public String loadItemDescService(Long id) {
		// TODO Auto-generated method stub
		return itemDescServiceProxy.getItemDesc(id).getItemDesc();
	}
	
	
	
	

}
