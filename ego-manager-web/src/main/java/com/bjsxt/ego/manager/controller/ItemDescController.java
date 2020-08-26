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
import com.bjsxt.ego.beans.TreeNode;
import com.bjsxt.ego.manager.service.ManagerItemDescService;

@Controller
public class ItemDescController {
	
	@Autowired
	private ManagerItemDescService managerItemDescServiceProxy;
	
	//商品描述信息的查询
	@RequestMapping(value="query/item/desc/{itemId}",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult itemDesc(@PathVariable Long itemId){
		
		return managerItemDescServiceProxy.getItemDescService(itemId);
		
	}

}
