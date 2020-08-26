package com.bjsxt.ego.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	
	/*商城首页*/
	@RequestMapping("/{url}")
	public String loadPage(@PathVariable String url){
		
		return url;
	}

}
