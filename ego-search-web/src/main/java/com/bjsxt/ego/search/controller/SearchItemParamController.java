package com.bjsxt.ego.search.controller;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjsxt.ego.search.service.SearchParamItemService;

@Controller
public class SearchItemParamController {
	
	
	@Autowired
	private SearchParamItemService searchParamItemService;
	
	
	//处理加载商品描述信息
	@RequestMapping(value="/item/param/{id}",produces=MediaType.TEXT_EVENT_STREAM_VALUE+";charset=UTF-8")
	@ResponseBody
	public String ItemParam(@PathVariable Long id){
		return  searchParamItemService.loadItemParamService(id);
		
		
		
	}

}
