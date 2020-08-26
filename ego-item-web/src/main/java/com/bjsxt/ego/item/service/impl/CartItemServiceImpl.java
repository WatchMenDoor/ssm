package com.bjsxt.ego.item.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bjsxt.ego.beans.JsonUtils;
import com.bjsxt.ego.item.dao.CartItemDao;
import com.bjsxt.ego.item.entity.CartItem;
import com.bjsxt.ego.item.service.CartItemService;
import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.rpc.service.ItemService;

@Service
public class CartItemServiceImpl implements CartItemService{
	
	
	@Autowired
	private CartItemDao cartItemDao;
	
	@Autowired
	private ItemService itemServiceProxy;
	

	@Override
	public void addItemToCartService(Long itemid, Long uid) {
		// TODO Auto-generated method stub
		Map<String,String> map = null;
		CartItem cartItem = null;
		
		//判断用户是否第一次购物,或者判断是否存在carMap
						map = cartItemDao.loadCartMap(String.valueOf(uid));
						if(map.isEmpty()){
							map = new HashMap<>();
						}
		
		//获得商品对象
		TbItem item = itemServiceProxy.loadTbItemById(itemid);
		
	   
		//判断itemid对象的商品是否存在一个购物车对象
	    String cartItemStr = cartItemDao.loadCartItem(String.valueOf(uid), String.valueOf(itemid));
	    
	    if(StringUtils.isEmpty(cartItemStr)){
	    	 cartItem = new CartItem();
	    	 cartItem.setTbItem(item);
	 		cartItem.setNum(1);
	    }else{
	    	cartItem = JsonUtils.jsonToPojo(cartItemStr, CartItem.class);
	    	cartItem.setNum(cartItem.getNum()+1);
	    }
		
		
		//
		String json = JsonUtils.objectToJson(cartItem);
		//购物车对象放入map集合
		map.put(String.valueOf(itemid), json);
		
		//将map集合放入redis
		cartItemDao.addCartMap(String.valueOf(uid), map);
		
	}


	@Override
	public Map<Long, CartItem> loadCartItemListService(Long uid) {
		// TODO Auto-generated method stub
		Map<Long, CartItem> cartMap = new HashMap<>();
		//获得购物车列表
		Map<String, String> cartMapStr = cartItemDao.loadCartMap(String.valueOf(uid));
		if(!StringUtils.isEmpty(cartMapStr)){
			for (Entry<String,String> e : cartMapStr.entrySet()) {
				String key = e.getKey();
				String value = e.getValue();
				//将value字符串转换为cartItem
				CartItem item = JsonUtils.jsonToPojo(value, CartItem.class);
				cartMap.put(Long.parseLong(key), item);
			}
		}
		
		return cartMap;
	}

    //需要修改的购物车对象
	@Override
	public String updateCartItemNumService(Long itemid, Long uid,Integer num) {
		// TODO Auto-generated method stub
		try {
			//购物车对象
			String cartItemStr = cartItemDao.loadCartItem(String.valueOf(uid), String.valueOf(itemid));
			
			CartItem cartItem = JsonUtils.jsonToPojo(cartItemStr, CartItem.class);
			
			cartItem.setNum(num);
			
			//修改redis中商品的数量
			cartItemDao.updateCartMapNum(String.valueOf(uid), String.valueOf(itemid), JsonUtils.objectToJson(cartItem));
			
			return "ok";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


	
	//删除购物车
	@Override
	public void deleteCartItemNumService(Long itemid, Long uid) {
		// TODO Auto-generated method stub
		
		cartItemDao.deleteCartMapNum(String.valueOf(uid), String.valueOf(itemid));
		
	}

    //清空购物车
	@Override
	public void deleteCartItemAllService(Long uid) {
		// TODO Auto-generated method stub
		cartItemDao.deleteCartMapAll(String.valueOf(uid));
	}
	
	
	
	
	
	
	

}
