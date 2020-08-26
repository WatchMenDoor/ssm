package com.bjsxt.ego.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.rpc.pojo.TbUser;

public interface SsoUserService {

	public EgoResult loadUserByCondService(String cond,Integer type);
	
	public EgoResult saveUserService(TbUser tbUser);
	
	public EgoResult selectUserService(String username,String password,
			HttpServletRequest request,HttpServletResponse response);
	
	//从redis中获得用户状态
	public EgoResult loadUserByToken(String token);
	
	//用户登录状态的删除
	public EgoResult deleteUserByToken(String token);
}
