package com.bjsxt.ego.rpc.service;

import java.util.List;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResults;
import com.bjsxt.ego.rpc.pojo.TbContent;

public interface TbContentService {

	
	public PageResults<TbContent> loadTbContentListService(Long cid,Integer page,Integer rows);
	//内容的添加
	public EgoResult saveTbContentService(TbContent tbContent);
	//内容的删除
	public EgoResult deleteTbContentService(List<Long> ids);
	
	//内容的更新
	public EgoResult updateTbContentService(TbContent tbContent);
	
	//加载某个类目对应的内容列表
	public List<TbContent> loadTbContentByCidService(Long cid);
}
