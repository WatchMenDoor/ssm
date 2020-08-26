package com.bjsxt.ego.manager.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.FtpUtils;
import com.bjsxt.ego.beans.IDUtils;
import com.bjsxt.ego.beans.PageResults;
import com.bjsxt.ego.beans.PictureResult;
import com.bjsxt.ego.manager.service.ManagerItemService;
import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.rpc.pojo.TbItemDesc;
import com.bjsxt.ego.rpc.pojo.TbItemParam;
import com.bjsxt.ego.rpc.pojo.TbItemParamItem;
import com.bjsxt.ego.rpc.service.ItemService;
@Service
public class ManagerItemServiceImpl implements ManagerItemService{
	
	@Override
	public EgoResult reshelfItem(Long[] ids) {
		// TODO Auto-generated method stub
		List<Long> list = Arrays.asList(ids);
		return itemServiceProxy.updateItemStatus(list,true);
	}

	@Override
	public EgoResult instockItem(Long[] ids) {
		// TODO Auto-generated method stub
		List<Long> list = Arrays.asList(ids);
		return itemServiceProxy.updateItemStatus(list,false);
	}

	@Autowired
	private ItemService itemServiceProxy;

	@Override
	public PageResults<TbItem> selectItemListService(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return itemServiceProxy.selectItemList(page, rows);
	}

	@Override
	public EgoResult deleteItem(Long[] ids) {
		// TODO Auto-generated method stub
		
		List<Long> list = Arrays.asList(ids);
		return itemServiceProxy.deleteItem(list);
	}

	@Override
	public PictureResult uploadItemPic(MultipartFile file) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		String fileName = null;
		try {
			//获得新的文件名字
			fileName = IDUtils.genImageName();
			String oriname = file.getOriginalFilename();
			String etx = oriname.substring(oriname.lastIndexOf("."));
			
			fileName = fileName+etx;
			
			InputStream inputStream = file.getInputStream();
			
			//文件上传
			flag = FtpUtils.uploadFile(FTP_HOST,FTP_PORT, FTP_USERNAME, FTP_PASSWORD,FTP_PATH,fileName,inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		
		PictureResult result =null;
		if(flag){
			result = new PictureResult();
			result.setError(0);
			result.setUrl(IMAGE_HTTP_PATH+"/"+fileName);
			result.setMessage("ok");
			
		}else{
			result = new PictureResult();
			result.setError(1);
			result.setUrl(IMAGE_HTTP_PATH+"/"+fileName);
			result.setMessage("error");
			
		}
		return result;
	}
	
	//通过spring的EL表达式注入ftp信息
	@Value("${FTP_HOST}")
	private String FTP_HOST;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_PATH}")
	private String FTP_PATH;
	@Value("${IMAGE_HTTP_PATH}")
	private String IMAGE_HTTP_PATH;
	
	
	//商品新增
	@Override
	public EgoResult saveItemService(TbItem tbItem, String desc,String paramData) {
		// TODO Auto-generated method stub
		Date date = new Date();
		//封装item
		Long id =IDUtils.genItemId();
		tbItem.setId(id);
		tbItem.setStatus((byte)1);
		tbItem.setCreated(date);
		tbItem.setUpdated(date);
		TbItemDesc tbItemDesc = new TbItemDesc();
		
		tbItemDesc.setCreated(date);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setItemId(tbItem.getId());
		tbItemDesc.setUpdated(date);
		
		TbItemParamItem tbItemParamItem = new TbItemParamItem();
		tbItemParamItem.setItemId(id);
		tbItemParamItem.setParamData(paramData);
		tbItemParamItem.setCreated(date);
		tbItemParamItem.setUpdated(date);
		return itemServiceProxy.saveItem(tbItem, tbItemDesc,tbItemParamItem);
	}
    //商品的更新
	@Override
	public EgoResult updateItemService(TbItem tbItem, String desc,String paramData) {
		// TODO Auto-generated method stub
		
		Date date = new Date();
		//封装item
		tbItem.setUpdated(date);
		TbItemDesc tbItemDesc = new TbItemDesc();
		
		tbItemDesc.setCreated(date);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setItemId(tbItem.getId());
		tbItemDesc.setUpdated(date);
		
		TbItemParamItem tbItemParamItem = new TbItemParamItem();
		tbItemParamItem.setParamData(paramData);
		tbItemParamItem.setItemId(tbItem.getId());
		tbItemParamItem.setUpdated(date);
		return  itemServiceProxy.updateItem(tbItem, tbItemDesc, tbItemParamItem);
		
	}
	
	
	
	
	
	
	
	

}
