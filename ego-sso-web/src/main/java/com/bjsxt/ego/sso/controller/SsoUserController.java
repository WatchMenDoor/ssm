package com.bjsxt.ego.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.rpc.pojo.TbUser;
import com.bjsxt.ego.sso.service.SsoUserService;

@Controller
public class SsoUserController {
	
	@Autowired
	private SsoUserService ssoUserService;
	
	/*用户名唯一性验证*/
	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public MappingJacksonValue userCheck(@PathVariable String param ,@PathVariable Integer type,@RequestParam(required=false)String callback){
		EgoResult result = ssoUserService.loadUserByCondService(param, type);
		MappingJacksonValue value = new MappingJacksonValue(result);
		if(!StringUtils.isEmpty(callback)){
			value.setJsonpFunction(callback);
		}
		
		return value;
	}
	
	//用户注册请求
	@RequestMapping(value="/user/register",method=RequestMethod.POST)
	@ResponseBody
	public EgoResult userSave(TbUser tbUser){
		
		
		return ssoUserService.saveUserService(tbUser);
	}
	
	//用户登录请求
		@RequestMapping(value="/user/login",method=RequestMethod.POST)
		@ResponseBody
		public EgoResult userLogin(String username,String password,HttpServletRequest request,HttpServletResponse response){
			
			
			return ssoUserService.selectUserService(username, password,request,response);
		}
		
		
		/*获得用户状态的请求*/
		@RequestMapping("/user/token/{token}")
		@ResponseBody
		public MappingJacksonValue userToken(@PathVariable String token ,@RequestParam(required=false)String callback){
			EgoResult result = ssoUserService.loadUserByToken(token);
			MappingJacksonValue value = new MappingJacksonValue(result);
			if(!StringUtils.isEmpty(callback)){
				value.setJsonpFunction(callback);
			}
			
			return value;
		}
		
		
		/*删除用户状态的请求*/
		@RequestMapping("/user/logout/{token}")
		@ResponseBody
		public MappingJacksonValue userLogout(@PathVariable String token ,@RequestParam(required=false)String callback){
			EgoResult result = ssoUserService.deleteUserByToken(token);
			MappingJacksonValue value = new MappingJacksonValue(result);
			if(!StringUtils.isEmpty(callback)){
				value.setJsonpFunction(callback);
			}
			
			return value;
		}
}
