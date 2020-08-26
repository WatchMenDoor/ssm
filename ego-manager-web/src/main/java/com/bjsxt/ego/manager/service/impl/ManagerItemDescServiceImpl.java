package com.bjsxt.ego.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.manager.service.ManagerItemDescService;
import com.bjsxt.ego.rpc.pojo.TbItemDesc;
import com.bjsxt.ego.rpc.service.ItemDescService;

@Service
public class ManagerItemDescServiceImpl implements ManagerItemDescService{
	
	@Autowired
	private ItemDescService itemDescServiceProxy;

	

	@Override
	public EgoResult getItemDescService(Long itemId) {
		// TODO Auto-generated method stub
      TbItemDesc itemDesc = itemDescServiceProxy.getItemDesc(itemId);
		
		if(itemDesc!=null){
			return new EgoResult(itemDesc);
		}
		return new EgoResult(null);
	}
	
	
	

}
