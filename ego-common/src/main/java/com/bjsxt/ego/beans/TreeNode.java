package com.bjsxt.ego.beans;
/*封装树控件需要的模*/
public class TreeNode {
	private Long id;
	private String text;
	private String state;
	
	public TreeNode() {
		super();
	}
	public TreeNode(Long id, String text, String state) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	

}
