package com.bjsxt.ego.rpc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.rpc.mapper.TbUserMapper;
import com.bjsxt.ego.rpc.pojo.TbUser;
import com.bjsxt.ego.rpc.pojo.TbUserExample;
import com.bjsxt.ego.rpc.pojo.TbUserExample.Criteria;
import com.bjsxt.ego.rpc.service.TbUserService;

@Service
public class TbUserServiceImpl implements TbUserService{
	
	
	@Autowired
	private TbUserMapper tbUserMapper;

	@Override
	public EgoResult loadTbUserByCondService(String cond, Integer type) {
		// TODO Auto-generated method stub
		
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		if(type.equals(1)){
			criteria.andUsernameEqualTo(cond);
		}else if(type.equals(2)){
			criteria.andPhoneEqualTo(cond);
		}else{
			criteria.andEmailEqualTo(cond);
		}
		List<TbUser> list = tbUserMapper.selectByExample(example);
		EgoResult result = new EgoResult();
		result.setStatus(200);
		result.setMsg("ok");
		if(list!=null&&list.size()>0){
			result.setData(false);
		}else{
			result.setData(true);
		}
		
		return result;
	}

	
	//用户注册
	@Override
	public EgoResult saveUserService(TbUser TbUser) {
		// TODO Auto-generated method stub
		EgoResult result = new EgoResult();
		try {
			Date date = new Date();
			TbUser.setCreated(date);
			TbUser.setUpdated(date);
			tbUserMapper.insert(TbUser);
			result.setStatus(200);
			result.setMsg("注册成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatus(400);
			result.setMsg("注册失败，请稍后再试");
		}
		return result;
	}

    //用户登录的验证
	@Override
	public TbUser selectUserByUserName(String uname) {
		// TODO Auto-generated method stub
		
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(uname);
		List<TbUser> list = tbUserMapper.selectByExample(example);
		if(list!=null&&list.size()==1)
			return list.get(0);
		return null;
	}
	
	
	
	
	
	

}
