package com.bjsxt.ego.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResults;
import com.bjsxt.ego.beans.PictureResult;
import com.bjsxt.ego.manager.service.ManagerItemParamService;
import com.bjsxt.ego.rpc.pojo.TbItemParam;

@Controller
public class ItemParamController {
	
	@Autowired
	private ManagerItemParamService managerItemParamService;
	
	@RequestMapping(value="/item/param/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public PageResults<TbItemParam> itemParamList(@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="30")Integer rows){
		return managerItemParamService.loadItemParamListService(page, rows);
	}
	
	//根据商品类目id查询规格参数
	@RequestMapping(value="/item/param/query/{cid}",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult itemParamQuery(@PathVariable Long cid){
		return managerItemParamService.loadItemParamByCidService(cid);
	}

	
	   //新增商品规格参数模板的请求
		@RequestMapping(value="/item/param/save/{cid}",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
		@ResponseBody
		public EgoResult saveItemParam(@PathVariable Long cid,String paramData){
			return managerItemParamService.saveItemParamByCidService(cid, paramData);
		}
		
		 //商品规格批量删除的请求
		@RequestMapping(value="/item/param/delete",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
		@ResponseBody
		public EgoResult deleteItemParam(Long[] ids){
			return managerItemParamService.deleteItemParamService(ids);
		}
		//商品规格参数的显示
		@RequestMapping(value="/item/param/select/{cid}",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
		@ResponseBody
		public EgoResult itemParamSelect(@PathVariable Long cid){
			return managerItemParamService.loadItemParamByCidService(cid);
		}
}
