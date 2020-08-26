package com.bjsxt.ego.manager.service;

import java.util.List;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResults;
import com.bjsxt.ego.rpc.pojo.TbItemParam;

//商品规格
public interface ManagerItemParamService {
	
	public PageResults<TbItemParam> loadItemParamListService(Integer page,Integer rows);
	
	public EgoResult loadItemParamByCidService(Long cid);
	
	//新增规格参数模板
	public EgoResult saveItemParamByCidService(Long cid,String paramData);
	
	//批量删除规格参数
	public EgoResult deleteItemParamService(Long[] ids);
}
