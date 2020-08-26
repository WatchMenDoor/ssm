package com.bjsxt.ego.rpc.service;

import java.util.List;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.rpc.pojo.TbContentCategory;

public interface TbContentCatogeryService {
	//加载内容树
	public List<TbContentCategory> loadTbContentCateGoryByPidService(Long pid);
	
	//添加内容分类节点
	public EgoResult saveTbContentCategory(TbContentCategory tbContentCategory);
	
	//删除内容分类节点
	public void deleteTbContentCategory(Long id);
	//更新内容分类节点

}
