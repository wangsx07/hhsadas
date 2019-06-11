package com.jeesite.modules.car.utils;

import java.io.Serializable;
import java.util.Map;

public class ReturnJson implements Serializable{

	private static final long serialVersionUID = 1L;
	private String msg;
	private int code;
	private Map  data;
	
	public ReturnJson() {}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Map getData() {
		return data;
	}
	public void setDate(Map data) {
		this.data = data;
	}
	public ReturnJson(String msg, int code, Map data) {
		super();
		this.msg = msg;
		this.code = code;
		this.data = data;
	}
	@Override
	public String toString() {
		return "ReturnJson [msg=" + msg + ", code=" + code + ", date=" + data + "]";
	}
	
}
