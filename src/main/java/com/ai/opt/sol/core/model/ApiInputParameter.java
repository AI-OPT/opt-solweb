package com.ai.opt.sol.core.model;
/**
 * API输入参数
 *
 * Date: 2015年9月23日 <br>
 * Copyright (c) 2015 asiainfo.com <br>
 * @author gucl
 */
public class ApiInputParameter {
	/**
	 * 参数索引ID
	 */
	private String id;
	/**
	 * 参数名称
	 */
	private String paramName;
	/**
	 * 参数类型
	 */
	private String className;
	/**
	 * 参数说明
	 */
	private String commentText;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	
}
