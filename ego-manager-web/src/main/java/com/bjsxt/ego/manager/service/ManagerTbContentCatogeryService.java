package com.bjsxt.ego.manager.service;

import java.util.List;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.TreeNode;
import com.bjsxt.ego.rpc.pojo.TbContentCategory;

public interface ManagerTbContentCatogeryService {

	
	public List<TreeNode> loadContentCategoryService(Long pid);
	
	//添加分类节点
	public EgoResult saveContentCategoryService(TbContentCategory tbContentCategory);
	//删除分类节点
	public void deleteContentCategoryService(Long id);
}
