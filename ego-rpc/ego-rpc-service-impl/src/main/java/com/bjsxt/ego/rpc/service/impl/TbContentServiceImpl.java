package com.bjsxt.ego.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResults;
import com.bjsxt.ego.rpc.mapper.TbContentMapper;
import com.bjsxt.ego.rpc.pojo.TbContent;
import com.bjsxt.ego.rpc.pojo.TbContentExample;
import com.bjsxt.ego.rpc.pojo.TbContentExample.Criteria;
import com.bjsxt.ego.rpc.service.TbContentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class TbContentServiceImpl implements TbContentService {
	
	
	@Autowired
	private TbContentMapper tbContentMapper;

	//查询该分类下的内容
	@Override
	public PageResults<TbContent> loadTbContentListService(Long cid,Integer page,Integer rows) {
		// TODO Auto-generated method stub
		
		try {
			PageResults<TbContent>  result= new PageResults<TbContent>();
			Page pg = PageHelper.startPage(page, rows);
			TbContentExample example = new TbContentExample();
			Criteria criteria = example.createCriteria();
			criteria.andCategoryIdEqualTo(cid);
			List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
			
			result.setRows(list);
			result.setTotal(pg.getTotal());
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	//商品的添加
	@Override
	public EgoResult saveTbContentService(TbContent tbContent) {
		// TODO Auto-generated method stub
		try {
			tbContentMapper.insert(tbContent);
			return EgoResult.ok();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	//商品删除
	@Override
	public EgoResult deleteTbContentService(List<Long> ids) {
		// TODO Auto-generated method stub
		try {
			TbContentExample example = new TbContentExample();
			Criteria criteria = example.createCriteria();
			criteria.andIdIn(ids);
			tbContentMapper.deleteByExample(example);
			return EgoResult.ok();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//商品的更新
	@Override
	public EgoResult updateTbContentService(TbContent tbContent) {
		// TODO Auto-generated method stub
		
		try {
			tbContentMapper.updateByPrimaryKeySelective(tbContent);
			return EgoResult.ok();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	//对应类目
	@Override
	public List<TbContent> loadTbContentByCidService(Long cid) {
		// TODO Auto-generated method stub
		try {
			TbContentExample example = new TbContentExample();
			Criteria criteria = example.createCriteria();
			criteria.andCategoryIdEqualTo(cid);
			List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	

}
