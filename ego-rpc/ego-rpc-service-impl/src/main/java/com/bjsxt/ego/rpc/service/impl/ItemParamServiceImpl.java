package com.bjsxt.ego.rpc.service.impl;

import java.util.List;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResults;
import com.bjsxt.ego.rpc.mapper.TbItemParamItemMapper;
import com.bjsxt.ego.rpc.mapper.TbItemParamMapper;
import com.bjsxt.ego.rpc.pojo.TbItemParam;
import com.bjsxt.ego.rpc.pojo.TbItemParamExample;
import com.bjsxt.ego.rpc.pojo.TbItemParamExample.Criteria;
import com.bjsxt.ego.rpc.pojo.TbItemParamItem;
import com.bjsxt.ego.rpc.pojo.TbItemParamItemExample;
import com.bjsxt.ego.rpc.service.ItemParamService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class ItemParamServiceImpl implements ItemParamService{
	
	@Autowired
	private TbItemParamMapper tbItemParamMapper;

	@Override
	public PageResults<TbItemParam> loadTbItemParamService(Integer page,Integer rows) {
		// TODO Auto-generated method stub
		
		PageResults<TbItemParam> result = new PageResults<>();
		
		Page pg = PageHelper.startPage(page, rows);
		//查询大字段
		TbItemParamExample example = new TbItemParamExample();
		
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		
		result.setRows(list);
		result.setTotal(pg.getTotal());
		
		
		return result;
	}
	
	//判断该类目是否已经添加模板

	@Override
	public TbItemParam loadTbItemParaByCidService(Long cid) {
		// TODO Auto-generated method stub
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()==1){
			return list.get(0);
		}
		return null;
	}

	
	//商品规格参数的新增
	@Override
	public EgoResult saveTbItemParamService(TbItemParam tbItemParam) {
		// TODO Auto-generated method stub
		
		try {
			tbItemParamMapper.insert(tbItemParam);
			return EgoResult.ok();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	//商品规格批量删除
	@Override
	public EgoResult deleteTbItemParamService(List<Long> ids) {
		// TODO Auto-generated method stub
		
		try {
			TbItemParamExample example = new TbItemParamExample();
			Criteria criteria = example.createCriteria();
			criteria.andIdIn(ids);
			tbItemParamMapper.deleteByExample(example);
			return EgoResult.ok();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	

}
