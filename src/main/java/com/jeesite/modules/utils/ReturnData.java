package com.jeesite.modules.utils;

import java.io.Serializable;
import java.util.Map;

public class ReturnData implements Serializable{
	private static final long serialVersionUID = 1L;
	private String message;
	private int code;
	private Map returnMap;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Map getReturnMap() {
		return returnMap;
	}
	public void setReturnMap(Map returnMap) {
		this.returnMap = returnMap;
	}
	public ReturnData(String message, int code, Map returnMap) {
		super();
		this.message = message;
		this.code = code;
		this.returnMap = returnMap;
	}
}
