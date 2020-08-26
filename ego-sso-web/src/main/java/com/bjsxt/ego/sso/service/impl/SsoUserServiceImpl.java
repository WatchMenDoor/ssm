package com.bjsxt.ego.sso.service.impl;

import java.rmi.dgc.DGC;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.bjsxt.ego.beans.CookieUtils;
import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.JsonUtils;
import com.bjsxt.ego.rpc.pojo.TbUser;
import com.bjsxt.ego.rpc.service.TbUserService;
import com.bjsxt.ego.sso.service.SsoUserService;

import redis.clients.jedis.JedisCluster;
@Service
public class SsoUserServiceImpl implements SsoUserService{
	
	
	@Autowired
	private TbUserService tbUserServiceProxy;
	
	@Autowired
	private JedisCluster cluster;

	@Override
	public EgoResult loadUserByCondService(String cond, Integer type) {
		// TODO Auto-generated method stub
		return tbUserServiceProxy.loadTbUserByCondService(cond, type);
	}

	
	//注册
	@Override
	public EgoResult saveUserService(TbUser tbUser) {
		// TODO Auto-generated method stub
		String pwd = tbUser.getPassword();
		String md5 = DigestUtils.md5DigestAsHex(pwd.getBytes());
		tbUser.setPassword(md5);
		return tbUserServiceProxy.saveUserService(tbUser);
	}

    //用户登录
	@Override
	public EgoResult selectUserService(String username, String password,HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		EgoResult result = new EgoResult();
		
		TbUser user = tbUserServiceProxy.selectUserByUserName(username);
		if(user!=null){
			//对密码进行加密
			password = DigestUtils.md5DigestAsHex(password.getBytes());
			if(password.equals(user.getPassword())){
				
				String json = JsonUtils.objectToJson(user);
				//将用户信息保存到redis
				String token = UUID.randomUUID().toString();
				cluster.set(token, json);
				cluster.expire(token, 1800);
				
				//将token值响应到客户端
				CookieUtils.setCookie(request, response, "sso_token", token);
				
				result.setData(token);
				result.setStatus(200);
				result.setMsg("ok");
				
				return result;
			}
		}
		result.setData(null);
		result.setStatus(400);
		result.setMsg("error");
		return result;
	}

    //获得用户状态
	@Override
	public EgoResult loadUserByToken(String token) {
		// TODO Auto-generated method stub
		
		EgoResult result = new EgoResult();
		
		String string = cluster.get(token);
		if(!StringUtils.isEmpty(string)){
			//转换为对象把保存
			TbUser tbUser = JsonUtils.jsonToPojo(string, TbUser.class);
			result.setData(tbUser);
			result.setStatus(200);
			result.setMsg("ok");
		}
		
		result.setData(null);
		result.setStatus(400);
		result.setMsg("error");
		return result;
	}

    //删除用户状态
	@Override
	public EgoResult deleteUserByToken(String token) {
		// TODO Auto-generated method stub
		
		EgoResult result = new EgoResult();
		
		Long del = cluster.del(token);
		if(!del.equals(0L)){
			result.setData("");
			result.setStatus(200);
			result.setMsg("ok");
		}
		result.setData(null);
		result.setStatus(400);
		result.setMsg("error");
		
		return result;
	}
	
	
	
	

}
