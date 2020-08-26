package com.bjsxt.ego.order.service;

import java.util.List;
import java.util.Map;

import com.bjsxt.ego.order.entity.CartItem;
import com.bjsxt.ego.rpc.pojo.TbOrder;
import com.bjsxt.ego.rpc.pojo.TbOrderItem;
import com.bjsxt.ego.rpc.pojo.TbOrderShipping;

public interface OrderService {

	public Map<Long,CartItem> loadCartItemMapService(Long uid);
	
	//用户下订单
	public Map<String,String> saveOrderService(TbOrder tbOrder,Long uid, TbOrderShipping tbOrderShipping);
	
	//查看用户的订单列表
	public List<TbOrder> loadOrderList(Long uid);
	
	//查看订单明细
	public List<TbOrderItem> loadOrderItemList(String orderid);
}
