package com.bjsxt.ego.beans;

public class PictureResult {
	
	private Integer error;
	private String url;
	private String message;
	public PictureResult() {
		super();
	}
	public PictureResult(Integer error, String url, String message) {
		super();
		this.error = error;
		this.url = url;
		this.message = message;
	}
	public Integer getError() {
		return error;
	}
	public void setError(Integer error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
