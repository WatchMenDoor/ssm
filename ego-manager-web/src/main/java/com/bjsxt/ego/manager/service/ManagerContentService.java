package com.bjsxt.ego.manager.service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResults;
import com.bjsxt.ego.rpc.pojo.TbContent;

public interface ManagerContentService {

	//进行内容的分类查询
	public PageResults<TbContent> loadContentListService(Long cid,Integer page,Integer rows);
	
	//内容的新增
	public EgoResult saveContent(TbContent tbContent);
	
	//内容的删除
		public EgoResult deleteContent(String ids);
		
		//内容的新增
		public EgoResult updateContent(TbContent tbContent);
}
