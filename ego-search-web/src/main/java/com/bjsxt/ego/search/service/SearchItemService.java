package com.bjsxt.ego.search.service;

import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.search.entity.SearchResult;

public interface SearchItemService {

	//进行商品分页查询
	public SearchResult loadItemService(String item_keywords,Integer page);
	//查询商品的基本信息
	public TbItem loadItemService(Long id);
}
