package com.bjsxt.ego.rpc.service;

import java.util.List;

import com.bjsxt.ego.rpc.pojo.TbOrder;
import com.bjsxt.ego.rpc.pojo.TbOrderItem;
import com.bjsxt.ego.rpc.pojo.TbOrderShipping;

public interface TbOrderService {

	//完成订单信息的保存
	public void saveOrderService(TbOrder tbOrder,List<TbOrderItem> OrderItems,TbOrderShipping tbOrderShipping);
	
	//返回用户的订单列表
	public List<TbOrder> loadTborderListService(Long uid);
	
	//查看订单详细
	public List<TbOrderItem> loadTborderItemListService(Long orderid);
}
