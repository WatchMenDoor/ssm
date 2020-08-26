package com.bjsxt.ego.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.bjsxt.ego.beans.IDUtils;
import com.bjsxt.ego.beans.JsonUtils;
import com.bjsxt.ego.order.dao.CartItemDao;
import com.bjsxt.ego.order.entity.CartItem;
import com.bjsxt.ego.order.service.OrderService;
import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.rpc.pojo.TbOrder;
import com.bjsxt.ego.rpc.pojo.TbOrderItem;
import com.bjsxt.ego.rpc.pojo.TbOrderShipping;
import com.bjsxt.ego.rpc.service.TbOrderService;

public class OrderServiceImpl implements OrderService {
	
	
	@Autowired
	private CartItemDao cartItemDao;
	
	@Autowired
	private TbOrderService tbOrderServiceProxy;
	
	@Override
	public Map<Long, CartItem> loadCartItemMapService(Long uid) {
		// TODO Auto-generated method stub
		
		Map<String, String> cartItemMapStr = cartItemDao.loadCartItemMap(String.valueOf(uid));
		
		Map<Long,CartItem> carMap = new HashMap<>();
		
		for (Entry<String,String> e:cartItemMapStr.entrySet()) {
			
			carMap.put(Long.parseLong(e.getKey()), JsonUtils.jsonToPojo(e.getValue(), CartItem.class));
			
		}
		return carMap;
	}

	//保存订单
	@Override
	public Map<String, String> saveOrderService(TbOrder tbOrder, Long uid, TbOrderShipping tbOrderShipping) {
		// TODO Auto-generated method stub
		
		try {
			//产生订单号
			String orderid =String.valueOf(IDUtils.genItemId());
			Date date = new Date();
			tbOrder.setOrderId(orderid);
			tbOrder.setPostFee("123");
			tbOrder.setStatus(2);
			tbOrder.setCreateTime(date);
			tbOrder.setUpdateTime(date);
			tbOrder.setPaymentTime(date);
			tbOrder.setConsignTime(date);
			tbOrder.setEndTime(date);
			tbOrder.setCloseTime(date);
			tbOrder.setShippingName("EMS");
			tbOrder.setShippingCode("110110");
			tbOrder.setUserId(uid);
			tbOrder.setBuyerMessage("message");
			tbOrder.setBuyerNick("9527");
			tbOrder.setBuyerRate(0);
			
			//获得用户购物车集合
			Map<Long,CartItem> cartMap = this.loadCartItemMapService(uid);
			//创建订单明细list
			List<TbOrderItem> list = new ArrayList<>();
			for (CartItem e:cartMap.values()) {
				 //创建订单明细对象
				TbOrderItem tbOrderItem = new TbOrderItem();
				
				String orderItemid =String.valueOf(IDUtils.genItemId());
				tbOrderItem.setOrderId(orderid);
				tbOrderItem.setId(orderItemid);
				//获得购物车中商品的对象
				TbItem item =  e.getTbItem();
   
				tbOrderItem.setItemId(String.valueOf(item.getId()));
				tbOrderItem.setNum(e.getNum());
				tbOrderItem.setOrderId(orderid);
				tbOrderItem.setTitle(item.getTitle());
				tbOrderItem.setPrice(item.getPrice());
				tbOrderItem.setTotalFee(item.getPrice()*e.getNum());
				tbOrderItem.setPicPath(item.getImages()[0]);
				list.add(tbOrderItem);
				
				
			}
			
      tbOrderShipping.setOrderId(orderid);
      tbOrderShipping.setCreated(date);
      tbOrderShipping.setReceiverPhone("110110");
      tbOrderShipping.setUpdated(date);
      
      tbOrderServiceProxy.saveOrderService(tbOrder, list, tbOrderShipping);

      Map<String, String> mapResult = new HashMap<String,String>();
      
      mapResult.put("orderId", orderid);
      mapResult.put("total",tbOrder.getPayment());
      

      cartItemDao.deleteCartItemMap(String.valueOf(uid));	 

			return mapResult;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<TbOrder> loadOrderList(Long uid) {
		// TODO Auto-generated method stub
		return tbOrderServiceProxy.loadTborderListService(uid);
	}

	@Override
	public List<TbOrderItem> loadOrderItemList(String orderid) {
		// TODO Auto-generated method stub
		return tbOrderServiceProxy.loadTborderItemListService(Long.parseLong(orderid));
	}
	
	
	
	
	
	

}
