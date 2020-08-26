package com.bjsxt.ego.beans;

import java.io.Serializable;
import java.util.List;



/*封装DataGird所需的数据模型*/
public class PageResults<T> implements Serializable{
	
	
	private List<T> rows;
	private Long total;
	public PageResults() {
		super();
	}
	
	public PageResults(List<T> rows, Long total) {
		super();
		this.rows = rows;
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
	

}
