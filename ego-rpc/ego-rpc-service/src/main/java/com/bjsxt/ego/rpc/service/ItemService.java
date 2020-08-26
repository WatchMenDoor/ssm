package com.bjsxt.ego.rpc.service;

import java.util.List;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResults;
import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.rpc.pojo.TbItemDesc;
import com.bjsxt.ego.rpc.pojo.TbItemParam;
import com.bjsxt.ego.rpc.pojo.TbItemParamItem;

public interface ItemService {

	/*实线商品信息查询*/
	
	public PageResults<TbItem> selectItemList(
			Integer page,Integer rows);
	
	/*上架下架*/
	
	public EgoResult updateItemStatus(List<Long> itemIds,Boolean flag);
	
	/*删除*/
	public EgoResult deleteItem(List<Long> itemIds);
	
	/*商品信息的保存*/
	public EgoResult saveItem(TbItem tbItem,TbItemDesc tbItemDesc,TbItemParamItem tbItemParamItem);
	
	//商品的更新
	public EgoResult updateItem(TbItem tbItem,TbItemDesc tbItemDesc,TbItemParamItem tbItemParamItem);
	
	//查看商品详细信息
	public TbItem loadTbItemById(Long id);
}
