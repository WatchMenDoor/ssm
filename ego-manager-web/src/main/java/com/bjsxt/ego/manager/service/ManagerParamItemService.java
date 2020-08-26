package com.bjsxt.ego.manager.service;

import com.bjsxt.ego.beans.EgoResult;

public interface ManagerParamItemService {

	
	//根据商品id加载商品规格信息
	public EgoResult loadParamItemService(Long itemid);
}
