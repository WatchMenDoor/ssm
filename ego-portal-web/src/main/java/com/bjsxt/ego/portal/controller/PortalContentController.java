package com.bjsxt.ego.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjsxt.ego.portal.service.PortalContentService;

@Controller
public class PortalContentController {
	
	@Autowired
	private PortalContentService portalContentService;
	
	//加载商品类目展示
		@RequestMapping(value="/content/index/list",produces=MediaType.TEXT_HTML_VALUE+";charset=UTF-8")
		@ResponseBody
		public String contentIndexList(Long categoryId){
			
			return portalContentService.loadContentListByCidService(categoryId);
		}

}
