package com.guonl.vo;

import java.io.Serializable;


public class Error implements Serializable {

	private Integer errcode = -1;//错误code
	
	private String errmsg = "请求失败";
	
	public Error() {}
	
	public Error(Integer errcode, String errmsg) {
		this.errcode = errcode;
		this.errmsg = errmsg;
	}

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
