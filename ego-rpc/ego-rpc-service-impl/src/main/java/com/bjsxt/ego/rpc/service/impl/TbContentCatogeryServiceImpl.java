package com.bjsxt.ego.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.rpc.mapper.TbContentCategoryMapper;
import com.bjsxt.ego.rpc.pojo.TbContentCategory;
import com.bjsxt.ego.rpc.pojo.TbContentCategoryExample;
import com.bjsxt.ego.rpc.pojo.TbContentCategoryExample.Criteria;
import com.bjsxt.ego.rpc.service.TbContentCatogeryService;

@Service
public class TbContentCatogeryServiceImpl implements TbContentCatogeryService {

	
	
	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	@Override
	public List<TbContentCategory> loadTbContentCateGoryByPidService(Long pid) {
		// TODO Auto-generated method stub
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(pid);
		return tbContentCategoryMapper.selectByExample(example);
		
	
	}
	@Override
	public EgoResult saveTbContentCategory(TbContentCategory tbContentCategory) {
		// TODO Auto-generated method stub
		
		EgoResult result = null;
		try {
			//更新父节点
			TbContentCategory record = new TbContentCategory();
			record.setIsParent(true);
			record.setParentId(tbContentCategory.getParentId());
			tbContentCategoryMapper.updateByPrimaryKeySelective(record);
			//添加内容分类节点
			 tbContentCategoryMapper.insert(tbContentCategory);
			 
			 result.setStatus(200);
			 result.setData(tbContentCategory);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return result;
	}
	
	//删除内容分类节点
	@Override
	public void deleteTbContentCategory(Long id) {
		// TODO Auto-generated method stub
		//更新父节点
		//获得当前父节点id
		TbContentCategory category = tbContentCategoryMapper.selectByPrimaryKey(id);
		Long pid = category.getParentId();
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(pid);
		int i = tbContentCategoryMapper.countByExample(example);
		if(i==1){
			TbContentCategory record = new TbContentCategory();
			record.setId(pid);
			record.setIsParent(false);
			tbContentCategoryMapper.updateByPrimaryKeySelective(record);
		}
		
		//删除节点
		tbContentCategoryMapper.deleteByPrimaryKey(id);
		
	}
	
	
	
	
	
	
	

}
