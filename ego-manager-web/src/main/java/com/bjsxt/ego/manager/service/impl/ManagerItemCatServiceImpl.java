package com.bjsxt.ego.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.TreeNode;
import com.bjsxt.ego.manager.service.ManagerItemCatService;
import com.bjsxt.ego.rpc.pojo.TbItemCat;
import com.bjsxt.ego.rpc.service.ItemCatService;
@Service
public class ManagerItemCatServiceImpl implements ManagerItemCatService{
	@Autowired
	private ItemCatService itemCatServiceProxy;

	@Override
	public List<TreeNode> getItemCatList(Long id) {
		// TODO Auto-generated method stub
		
		List<TbItemCat> itemCatListByParentId = itemCatServiceProxy.getItemCatListByParentId(id);
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
		TreeNode node = null;
		for (TbItemCat cat : itemCatListByParentId) {
			node =new TreeNode();
			node.setId(cat.getId());
			node.setState(cat.getIsParent()?"closed":"open");
			node.setText(cat.getName());
			nodeList.add(node);
			
		}
		
		
		return nodeList;
	}
	

}
