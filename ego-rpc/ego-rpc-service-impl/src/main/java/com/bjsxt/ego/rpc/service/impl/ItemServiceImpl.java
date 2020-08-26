package com.bjsxt.ego.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResults;
import com.bjsxt.ego.rpc.mapper.TbItemDescMapper;
import com.bjsxt.ego.rpc.mapper.TbItemMapper;
import com.bjsxt.ego.rpc.mapper.TbItemParamItemMapper;
import com.bjsxt.ego.rpc.mapper.TbItemParamMapper;
import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.rpc.pojo.TbItemDesc;
import com.bjsxt.ego.rpc.pojo.TbItemDescExample;
import com.bjsxt.ego.rpc.pojo.TbItemExample;
import com.bjsxt.ego.rpc.pojo.TbItemParam;
import com.bjsxt.ego.rpc.pojo.TbItemParamItem;
import com.bjsxt.ego.rpc.pojo.TbItemParamItemExample;
import com.bjsxt.ego.rpc.pojo.TbItemExample.Criteria;
import com.bjsxt.ego.rpc.service.ItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class ItemServiceImpl implements ItemService {
	
	//mapper对象
	@Autowired
	private TbItemMapper tbItemMapper;
	private Page startPage;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	
	
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;

	@Override
	public PageResults<TbItem> selectItemList(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		
		
		//分页操作
		Page ps = PageHelper.startPage(page, rows);
		
		
		//执行数据库执行操作
		TbItemExample example = new TbItemExample();
		
		List<TbItem> list = tbItemMapper.selectByExample(example);
		
		PageResults<TbItem> result = new PageResults<>();
		result.setRows(list);
		result.setTotal(ps.getTotal());
		return result;
	}

	@Override
	public EgoResult updateItemStatus(List<Long> itemIds, Boolean flag) {
		// TODO Auto-generated method stub
		
		TbItem item = new TbItem();
		if (flag) {
			item.setStatus((byte)1);
		}else{
			item.setStatus((byte)2);
		}
		
		TbItemExample example = new TbItemExample();
		
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(itemIds);
		tbItemMapper.updateByExampleSelective(item, example);
		return EgoResult.ok();
	}

	@Override
	public EgoResult deleteItem(List<Long> itemIds) {
		// TODO Auto-generated method stub
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(itemIds);
		tbItemMapper.deleteByExample(example);
		return EgoResult.ok();
	}

	@Override
	public EgoResult saveItem(TbItem tbItem, TbItemDesc tbItemDesc,TbItemParamItem tbItemParamItem) {
		// TODO Auto-generated method stub
		
		tbItemMapper.insert(tbItem);
		tbItemDescMapper.insert(tbItemDesc);
		tbItemParamItemMapper.insert(tbItemParamItem);
		return EgoResult.ok();
	}
   //商品的修改
	@Override
	public EgoResult updateItem(TbItem tbItem, TbItemDesc tbItemDesc,TbItemParamItem tbItemParamItem) {
		// TODO Auto-generated method stub
		
		tbItemMapper.updateByPrimaryKeySelective(tbItem);
		
		TbItemDescExample example = new TbItemDescExample();
		com.bjsxt.ego.rpc.pojo.TbItemDescExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(tbItemDesc.getItemId());
		
		
		Integer rows = tbItemDescMapper.countByExample(example);
		//判断商品是否有描述信息
		if (rows==0) {
			tbItemDescMapper.insert(tbItemDesc);	
		}else{
			tbItemDesc.setCreated(null);
			tbItemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
		}
		TbItemParamItemExample exam = new TbItemParamItemExample();
		com.bjsxt.ego.rpc.pojo.TbItemParamItemExample.Criteria criteria2 = exam.createCriteria();
		criteria2.andItemIdEqualTo(tbItemParamItem.getItemId());
		
		tbItemParamItemMapper.updateByExampleSelective(tbItemParamItem, exam);
		return EgoResult.ok();
	}

	
	//查询商品信息
	@Override
	public TbItem loadTbItemById(Long id) {
		// TODO Auto-generated method stub
		return tbItemMapper.selectByPrimaryKey(id);
	} 
	
	
	
		
	

}
