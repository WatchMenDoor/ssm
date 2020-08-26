package com.bjsxt.ego.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResults;
import com.bjsxt.ego.beans.TreeNode;
import com.bjsxt.ego.manager.service.ManagerContentService;
import com.bjsxt.ego.rpc.pojo.TbContent;

@Controller
public class ManagerContentController {
	
	
	@Autowired
	private ManagerContentService managerContentService;
	
	
	/*处理加载商品类目*/
	@RequestMapping(value="/content/category/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public PageResults<TbContent> contentQueryList(Long categoryId,Integer page,Integer rows){
		
		return managerContentService.loadContentListService(categoryId, page, rows);
		
	}
	
	/*商品内容新增*/
	@RequestMapping(value="/content/save",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult contentSave(TbContent tbContent){
		
		return managerContentService.saveContent(tbContent);
		
	}
	
	/*商品内容删除*/
	@RequestMapping(value="/content/delete",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult contentDelete(String ids){
		
		return managerContentService.deleteContent(ids);
		
	}
	
	/*商品内容更新*/
	@RequestMapping(value="/rest/content/edit",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult contentUpdate(TbContent tbContent){
		
		return managerContentService.updateContent(tbContent);
		
	}
}
