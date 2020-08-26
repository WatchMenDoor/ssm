package com.bjsxt.ego.search.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.rpc.service.ItemCatService;
import com.bjsxt.ego.rpc.service.ItemService;
import com.bjsxt.ego.search.dao.ItemDao;
import com.bjsxt.ego.search.entity.Item;
import com.bjsxt.ego.search.entity.SearchResult;
import com.bjsxt.ego.search.service.SearchItemService;

@Service
public class SearchItemServiceImpl implements SearchItemService{
	
	
	@Autowired
	private ItemDao itemDao;

	@Override
	public SearchResult loadItemService(String item_keywords, Integer page) {
		// TODO Auto-generated method stub
		
		SolrQuery params = new SolrQuery();
		
		//设置默认查询字段
		params.set("df", "item_keywords");
		//设置查询条件
		if(!StringUtils.isEmpty(item_keywords)){
			params.setQuery(item_keywords);
		}else{
			params.set("q","*");
		}
		
		//指定分页参数
		Integer rows = 20;
		//最小页判断
		if(page<1){
			page = 1;
		}
		
		Integer maxpage = 100;
		
		if(maxpage<page){
			page = maxpage;
		}
		Integer start = (page-1)*rows;
		params.setStart(start);
		params.setRows(rows);
		//设置高亮数据
		params.setHighlight(true);
		params.addHighlightField("title");
		params.setHighlightSimplePre("<font color='red'>");
		params.setHighlightSimplePost("</font>");
		
		//用dao进行查询
		QueryResponse responese = itemDao.loadItem(params);
		
		
		//本次查询到的文档集合
		SolrDocumentList docList = responese.getResults();
		
		
		//将docList转化为
		DocumentObjectBinder binder = new DocumentObjectBinder();
		List<Item> list = binder.getBeans(Item.class, docList);
		
		
		
		//获得本次查询的高亮数据
		Map<String, Map<String, List<String>>> hlts = responese.getHighlighting();
		
		
		
		for (Item t : list) {
			String id = t.getId();
			//获得某个商品信息高亮数据
			Map<String, List<String>> map = hlts.get(id);
			//获得某个商品的字段的高亮数据
			List<String> list2 = map.get("title");
			if(list2!=null&&list2.size()>0){
				t.setTitle(list2.get(0));
			}
			
		}
		//获得本次的总记录数
		long total = docList.getNumFound();
		
		SearchResult result = new SearchResult();
		result.setMaxpage(Long.parseLong(String.valueOf(maxpage)));
		result.setTotal(total);
		result.setList(list);
		
		
		return result;
	}

	@Autowired
	private ItemService itemServiceProxy;
	
	//查询商品信息
	@Override
	public TbItem loadItemService(Long id) {
		// TODO Auto-generated method stub
		return itemServiceProxy.loadTbItemById(id);
	}
	
	

}
