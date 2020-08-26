package com.bjsxt.ego.manager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.IDUtils;
import com.bjsxt.ego.beans.TreeNode;
import com.bjsxt.ego.manager.service.ManagerTbContentCatogeryService;
import com.bjsxt.ego.rpc.pojo.TbContentCategory;
import com.bjsxt.ego.rpc.service.TbContentCatogeryService;
@Service
public class ManagerTbContentCatogeryServiceImpl implements ManagerTbContentCatogeryService {
	
	
	@Autowired
	private TbContentCatogeryService tbContentCatogeryServiceProxy;
	

	@Override
	public List<TreeNode> loadContentCategoryService(Long pid) {
		// TODO Auto-generated method stub
		List<TreeNode> list = new ArrayList<>();
		
		
		List<TbContentCategory> clist = tbContentCatogeryServiceProxy.loadTbContentCateGoryByPidService(pid);
		
		for (TbContentCategory c :clist) {
			
			TreeNode node = new TreeNode();
			node.setId(c.getId());
			node.setState(c.getIsParent()?"closed":"open");
			node.setText(c.getName());
			list.add(node);
			
		}
		return list;
	}


	
	//新增
	@Override
	public EgoResult saveContentCategoryService(TbContentCategory tbContentCategory) {
		// TODO Auto-generated method stub
		Date date = new Date();
		Long id = IDUtils.genItemId();
		tbContentCategory.setId(id);
		tbContentCategory.setUpdated(date);
		tbContentCategory.setCreated(date);
		tbContentCategory.setStatus(1);
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setIsParent(false);
		return tbContentCatogeryServiceProxy.saveTbContentCategory(tbContentCategory);
	}


    //删除分类节点
	@Override
	public void deleteContentCategoryService(Long id) {
		// TODO Auto-generated method stub
		tbContentCatogeryServiceProxy.deleteTbContentCategory(id);
	}
	
	
	
	
	
	
	
	
	

}
