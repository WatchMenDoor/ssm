package com.bjsxt.ego.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResults;
import com.bjsxt.ego.beans.TreeNode;
import com.bjsxt.ego.manager.service.ManagerTbContentCatogeryService;
import com.bjsxt.ego.rpc.pojo.TbContentCategory;
import com.bjsxt.ego.rpc.pojo.TbItem;

@Controller
public class ManagerContentCatogeryController {
	
	@Autowired
	private ManagerTbContentCatogeryService managerTbContentCatogeryService;
	
	
	/*分页管理*/
	@RequestMapping(value="/content/category/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public List<TreeNode> contentList(@RequestParam(defaultValue="0")Long id){
		return managerTbContentCatogeryService.loadContentCategoryService(id);
	}
	
	
	/*添加分类节点的请求*/
	@RequestMapping(value="/content/category/create",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult contentSave(TbContentCategory tbContentCategory){
		return managerTbContentCatogeryService.saveContentCategoryService(tbContentCategory);
	}
	
	/*删除分类节点的请求*/
	@RequestMapping(value="/content/category/delete/",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult deleteContentCategory(Long id){
		 managerTbContentCatogeryService.deleteContentCategoryService(id);
		 return null;
	}

}
