package com.bjsxt.ego.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjsxt.ego.rpc.mapper.TbOrderItemMapper;
import com.bjsxt.ego.rpc.mapper.TbOrderMapper;
import com.bjsxt.ego.rpc.mapper.TbOrderShippingMapper;
import com.bjsxt.ego.rpc.pojo.TbOrder;
import com.bjsxt.ego.rpc.pojo.TbOrderExample;
import com.bjsxt.ego.rpc.pojo.TbOrderExample.Criteria;
import com.bjsxt.ego.rpc.pojo.TbOrderItem;
import com.bjsxt.ego.rpc.pojo.TbOrderItemExample;
import com.bjsxt.ego.rpc.pojo.TbOrderShipping;
import com.bjsxt.ego.rpc.service.TbOrderService;
@Service
public class TbOrderServiceImpl implements TbOrderService{
	
	@Autowired
	private TbOrderMapper tbOrderMapper;
	
	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;
	
	@Autowired
	private TbOrderShippingMapper tbOrderShippingMapper;
	
    @Transactional
	@Override
	public void saveOrderService(TbOrder tbOrder, List<TbOrderItem> OrderItems, TbOrderShipping tbOrderShipping) {
		// TODO Auto-generated method stub
		
		tbOrderMapper.insert(tbOrder);
		for (int i = 0; i < OrderItems.size(); i++) {
			tbOrderItemMapper.insert(OrderItems.get(i));
		}
		
		tbOrderShippingMapper.insert(tbOrderShipping);
		
	}

	@Override
	public List<TbOrder> loadTborderListService(Long uid) {
		// TODO Auto-generated method stub
		TbOrderExample example = new TbOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(uid);
		return tbOrderMapper.selectByExample(example);
		 
	}

	@Override
	public List<TbOrderItem> loadTborderItemListService(Long orderid) {
		// TODO Auto-generated method stub
		TbOrderItemExample example = new TbOrderItemExample();
		com.bjsxt.ego.rpc.pojo.TbOrderItemExample.Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(String.valueOf(orderid));
		
		List<TbOrderItem> list = tbOrderItemMapper.selectByExample(example);
		return list;
	}
	
	
	
    
    
	

}
