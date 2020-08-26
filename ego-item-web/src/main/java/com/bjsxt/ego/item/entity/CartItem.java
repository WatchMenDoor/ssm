package com.bjsxt.ego.item.entity;

import java.io.Serializable;

import com.bjsxt.ego.rpc.pojo.TbItem;

public class CartItem implements Serializable{

	
	
	private TbItem tbItem;
	private Integer num;
	public TbItem getTbItem() {
		return tbItem;
	}
	public void setTbItem(TbItem tbItem) {
		this.tbItem = tbItem;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	
}
