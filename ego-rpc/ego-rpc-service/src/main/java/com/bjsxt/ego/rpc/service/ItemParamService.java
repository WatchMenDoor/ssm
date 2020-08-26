package com.bjsxt.ego.rpc.service;

import java.util.List;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResults;
import com.bjsxt.ego.rpc.pojo.TbItemParam;


public interface ItemParamService {
	
	//商品规格参数的分页显示
	public PageResults<TbItemParam> loadTbItemParamService(Integer page,Integer rows);
	
	//判断规格是否已选
	public TbItemParam loadTbItemParaByCidService(Long cid);
	
	//商品规格参数的新增
	public EgoResult saveTbItemParamService(TbItemParam tbItemParam);
	
	//商品规格批量删除
	public EgoResult deleteTbItemParamService(List<Long> ids);
}
