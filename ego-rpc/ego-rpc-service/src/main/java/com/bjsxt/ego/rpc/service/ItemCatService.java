package com.bjsxt.ego.rpc.service;

import java.util.List;

import com.bjsxt.ego.rpc.pojo.TbItemCat;

public interface ItemCatService {
	/*根据某个节点的id查询对应的子节点集合*/
	public List<TbItemCat> getItemCatListByParentId(Long id);
	
	//加载门户首页的商品类目
	public List<TbItemCat> loadItemCatService();

}
