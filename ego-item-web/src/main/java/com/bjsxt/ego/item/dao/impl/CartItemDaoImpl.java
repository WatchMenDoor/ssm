package com.bjsxt.ego.item.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bjsxt.ego.beans.JsonUtils;
import com.bjsxt.ego.item.dao.CartItemDao;

import redis.clients.jedis.JedisCluster;
@Repository
public class CartItemDaoImpl implements CartItemDao{
	
	
	@Autowired
	private JedisCluster cluster;

	@Override
	public void addCartMap(String uid, Map<String, String>carMap ) {
		// TODO Auto-generated method stub
		
		cluster.hmset(uid, carMap);
		
	}

	//查询用户对应的购物车
	@Override
	public Map<String, String> loadCartMap(String uid) {
		// TODO Auto-generated method stub
		return cluster.hgetAll(uid);
	}

	@Override
	public String loadCartItem(String uid, String itemid) {
		// TODO Auto-generated method stub
		return cluster.hget(uid, itemid);
	}

	//修改redis中商品的数量
	@Override
	public void updateCartMapNum(String uid, String itemid, String cartIteStr) {
		// TODO Auto-generated method stub
		cluster.hset(uid,itemid,cartIteStr);
	}

	@Override
	public void deleteCartMapNum(String uid, String itemid) {
		// TODO Auto-generated method stub
		
		cluster.hdel(uid, itemid);
	}

	@Override
	public void deleteCartMapAll(String uid) {
		// TODO Auto-generated method stub
		cluster.del(uid);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
