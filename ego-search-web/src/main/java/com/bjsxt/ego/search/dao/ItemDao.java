package com.bjsxt.ego.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

public interface ItemDao {
	//商品索引库检索
   public QueryResponse loadItem(SolrQuery params);
}
