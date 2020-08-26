package com.bjsxt.ego.item.dao;

import java.util.Map;

import com.bjsxt.ego.item.entity.CartItem;

//访问数据库
public interface CartItemDao {
	
	
	public void addCartMap(String uid,Map<String,String> carMap);
	
	public Map<String,String> loadCartMap(String uid);
	
	//获得某个用户是否有特定商品
	public String loadCartItem(String uid,String itemid);
	
	//修改redis中商品的数量
	public void updateCartMapNum(String uid,String itemid,String cartIteStr);
	
	//删除商品
	public void deleteCartMapNum(String uid,String itemid);
	
	//清空购物车
		public void deleteCartMapAll(String uid);

}
