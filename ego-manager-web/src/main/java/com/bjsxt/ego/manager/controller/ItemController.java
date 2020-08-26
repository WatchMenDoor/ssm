package com.bjsxt.ego.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResults;
import com.bjsxt.ego.manager.service.ManagerItemService;
import com.bjsxt.ego.rpc.pojo.TbItem;

@Controller
public class ItemController {
	@Autowired
	private ManagerItemService managerItemService;
	
	
	/*分页管理*/
	@RequestMapping(value="item/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public PageResults<TbItem> itemList(@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="30")Integer rows){
		return managerItemService.selectItemListService(page, rows);
	}
	/*上下架管理*/
	@RequestMapping(value="item/reshelf",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")	
	@ResponseBody
	public EgoResult reshelfItem(Long[]ids){
		return managerItemService.reshelfItem(ids);
	}
	
	@RequestMapping(value="item/instock",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")	
	@ResponseBody
	public EgoResult instockItem(Long[]ids){
		return managerItemService.instockItem(ids);
	}
	
	/*删除商品*/
	@RequestMapping(value="item/delete",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")	
	@ResponseBody
	public EgoResult DeleteItem(Long[]ids){
		return managerItemService.deleteItem(ids);
	}

	
	/*保存商品*/
	@RequestMapping(value="item/save",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")	
	@ResponseBody
	public EgoResult saveItem(TbItem tbItem, String desc,String itemParams){
		return managerItemService.saveItemService(tbItem,desc,itemParams);
	}
	
	//更新商品
	@RequestMapping(value="/item/update",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")	
	@ResponseBody
	public EgoResult updateItem(TbItem tbItem, String desc,String itemParams){
		return managerItemService.updateItemService(tbItem, desc,itemParams);
	}
}
