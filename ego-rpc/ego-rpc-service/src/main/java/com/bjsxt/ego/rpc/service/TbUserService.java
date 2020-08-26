package com.bjsxt.ego.rpc.service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.rpc.pojo.TbUser;

public interface TbUserService {

	//用户唯一验证
	public EgoResult loadTbUserByCondService(String cond,Integer type);
	//用户注册
	public EgoResult saveUserService(TbUser TbUser);
	//用户登录
	public TbUser selectUserByUserName(String uname);
	
	
}
