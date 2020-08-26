package com.bjsxt.ego.order.dao;

import java.util.Map;



//访问数据库
public interface CartItemDao {
	
	
	
	//加载用户购物车集合
	public Map<String,String> loadCartItemMap(String uid);
	
	//下完清单后清空购物车
	public void deleteCartItemMap(String uid);
	
	

}
