package com.bjsxt.ego.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	/*商城首页*/
	@RequestMapping("/")
	public String showIndex(){
		System.out.println("=================");
		return "index";
	}
	
	/*加载其他页面*/
	@RequestMapping("{page}")
	public String showPage(@PathVariable String page){
		return page;
	}

}
