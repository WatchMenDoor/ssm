package com.bjsxt.ego.rpc.service;

import com.bjsxt.ego.rpc.pojo.TbItemDesc;

/*查询商品描述*/
public interface ItemDescService {
	
	public TbItemDesc getItemDesc(Long itemId);

}
