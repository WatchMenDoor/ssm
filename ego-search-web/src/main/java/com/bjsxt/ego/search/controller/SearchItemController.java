package com.bjsxt.ego.search.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.search.entity.SearchResult;
import com.bjsxt.ego.search.service.SearchItemService;

@Controller
public class SearchItemController {

	@Autowired
	private SearchItemService searchItemService;
	
	@RequestMapping("/{url}")
	public String loadPage(@PathVariable String url,String q,@RequestParam(defaultValue="1")Integer page,Model model){
		String kws = null;
		try {
			
			System.out.println("============"+q);
			kws = new String(q.getBytes("ISO-8859-1"),"UTF-8");
			
			
			//调用业务层方法
			SearchResult result = searchItemService.loadItemService(q, page);
			
			model.addAttribute("query", kws);
			model.addAttribute("itemList", result.getList());
			model.addAttribute("page",page);
			model.addAttribute("maxpage",result.getMaxpage());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return url;
	}
	
	
	//查询商品基本信息
	@RequestMapping("/item/{id}")
	public String loadItem(@PathVariable Long id,Model model){
		TbItem item = searchItemService.loadItemService(id);
		model.addAttribute("item",item);
		
		return "item";
	}
	
}
