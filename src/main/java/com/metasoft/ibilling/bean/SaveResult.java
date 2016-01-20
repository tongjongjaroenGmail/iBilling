package com.metasoft.ibilling.bean;

public class SaveResult {
	private int id;
	private boolean seccess = false;
	private String errorDesc;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isSeccess() {
		return seccess;
	}
	public void setSeccess(boolean seccess) {
		this.seccess = seccess;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
	
}
