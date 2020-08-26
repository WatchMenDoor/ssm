package com.bjsxt.ego.manager.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResults;
import com.bjsxt.ego.manager.service.ManagerItemParamService;
import com.bjsxt.ego.rpc.pojo.TbItemParam;
import com.bjsxt.ego.rpc.service.ItemParamService;

@Service
public class ManagerItemParamServiceImpl implements ManagerItemParamService {

	
	@Autowired
	private ItemParamService itemParamServiceProxy;
	
	//查询参数
	@Override
	public PageResults<TbItemParam> loadItemParamListService(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return itemParamServiceProxy.loadTbItemParamService(page, rows);
	}
    
	//选择该类目是否有参数
	@Override
	public EgoResult loadItemParamByCidService(Long cid) {
		// TODO Auto-generated method stub
		EgoResult result = null;
		TbItemParam tbItemParam = itemParamServiceProxy.loadTbItemParaByCidService(cid);
		result = new EgoResult();
		result.setStatus(200);
		result.setData(tbItemParam);
		
		return result;
	}

	
	//商品规格参数的新增
	@Override
	public EgoResult saveItemParamByCidService(Long cid, String paramData) {
		// TODO Auto-generated method stub
		TbItemParam tbItemParam = new TbItemParam();
		Date date = new Date();
		
		tbItemParam.setItemCatId(cid);
		tbItemParam.setParamData(paramData);
		tbItemParam.setCreated(date);
		tbItemParam.setUpdated(date);
		return itemParamServiceProxy.saveTbItemParamService(tbItemParam);
	}

	//商品规格参数批量删除
	@Override
	public EgoResult deleteItemParamService(Long[] ids) {
		// TODO Auto-generated method stub
		
		List<Long> list = Arrays.asList(ids);
		return itemParamServiceProxy.deleteTbItemParamService(list);
	}
	
	
	
	
	
	
	

}
