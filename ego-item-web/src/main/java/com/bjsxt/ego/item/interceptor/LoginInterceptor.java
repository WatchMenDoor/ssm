package com.bjsxt.ego.item.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bjsxt.ego.beans.CookieUtils;
import com.bjsxt.ego.beans.JsonUtils;
import com.bjsxt.ego.rpc.pojo.TbUser;

import redis.clients.jedis.JedisCluster;
//拦截
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private JedisCluster cluster;
 
	 //验证用户是否登录
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler)
			throws Exception {

		//获得凭证
		String token = CookieUtils.getCookieValue(request, "sso_token");
		//查询数据库
		String jsonStr = cluster.get(token);
		
		if(!StringUtils.isEmpty(token)){
			if(!StringUtils.isEmpty(jsonStr)){
				TbUser user = JsonUtils.jsonToPojo(jsonStr, TbUser.class);
				request.setAttribute("user", user);
				//登录，放行
				return true;
		}
		
		
		}
		
		
			//登录成功后返回的路径
			String url = request.getRequestURI().toString();
			//重定向到登录页面
			response.sendRedirect("http://localhost:8084/login?redirect="+url);
			
			return false;
		
		
	}
}
