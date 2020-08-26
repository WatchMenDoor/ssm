package com.bjsxt.ego.order.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bjsxt.ego.beans.JsonUtils;
import com.bjsxt.ego.order.dao.CartItemDao;

import redis.clients.jedis.JedisCluster;
@Repository
public class CartItemDaoImpl implements CartItemDao{
	
	
	@Autowired
	private JedisCluster cluster;

	
	
	@Override
	public Map<String, String> loadCartItemMap(String uid) {
		// TODO Auto-generated method stub
		return cluster.hgetAll(uid);
	}

	@Override
	public void deleteCartItemMap(String uid) {
		// TODO Auto-generated method stub
		cluster.del(uid);
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	

}
