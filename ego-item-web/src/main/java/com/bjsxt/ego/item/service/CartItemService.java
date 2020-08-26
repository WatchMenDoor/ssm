package com.bjsxt.ego.item.service;

import java.util.Map;

import com.bjsxt.ego.item.entity.CartItem;

public interface CartItemService {
	//将商品放入购物车
	public void addItemToCartService(Long itemid,Long uid);
	//加载用户购物车列表
	public Map<Long,CartItem> loadCartItemListService(Long uid);
	
	//修改数量
	public String updateCartItemNumService(Long itemid,Long uid,Integer num);
	
	
	//删除数量
	public void deleteCartItemNumService(Long itemid,Long uid);
	
	//清空购物车
   public void deleteCartItemAllService(Long uid);

}
