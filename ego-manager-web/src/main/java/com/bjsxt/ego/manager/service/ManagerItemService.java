package com.bjsxt.ego.manager.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResults;
import com.bjsxt.ego.beans.PictureResult;
import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.rpc.pojo.TbItemDesc;

public interface ManagerItemService {
/*商品分页查询*/
	public PageResults<TbItem> selectItemListService(Integer page,Integer rows);
	
	
	/*上架*/
	public EgoResult reshelfItem(Long[] ids);
	/*下架*/
	public EgoResult instockItem(Long[] ids);
	/*删除*/
	public EgoResult deleteItem(Long[] ids);
	
	/*完成商品图片上传*/
	public PictureResult uploadItemPic(MultipartFile file);
	//商品新增页面
	public EgoResult saveItemService(TbItem tbItem,String desc,String paramData);
	
	//商品更新页面
	public EgoResult updateItemService(TbItem tbItem,String desc,String paramData);
	
}
