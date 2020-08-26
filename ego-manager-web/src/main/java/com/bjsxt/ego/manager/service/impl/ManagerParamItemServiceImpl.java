package com.bjsxt.ego.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.manager.service.ManagerParamItemService;
import com.bjsxt.ego.rpc.pojo.TbItemParamItem;
import com.bjsxt.ego.rpc.service.ParamItemService;

@Service
public class ManagerParamItemServiceImpl implements ManagerParamItemService {
	
	@Autowired
	private ParamItemService paramItemServiceProxy;

	@Override
	public EgoResult loadParamItemService(Long itemid) {
		// TODO Auto-generated method stub
		EgoResult result = null;
		try {
			TbItemParamItem tbItemParamItem = paramItemServiceProxy.loadTbItemParamItemService(itemid);
			result = new EgoResult();
			result.setData(tbItemParamItem);
			result.setStatus(200);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

}
