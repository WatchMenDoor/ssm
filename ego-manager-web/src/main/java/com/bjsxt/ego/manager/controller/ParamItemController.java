package com.bjsxt.ego.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.manager.service.ManagerParamItemService;

@Controller
public class ParamItemController {
	
	
	@Autowired
	private ManagerParamItemService managerParamItemService;
	
	
	    //处理商品类目规格参数
		@RequestMapping(value="/param/item/query/{itemid}",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
		@ResponseBody
		public EgoResult itemParamQuery(@PathVariable Long itemid){
			return managerParamItemService.loadParamItemService(itemid);
		}
	
	
	

}
