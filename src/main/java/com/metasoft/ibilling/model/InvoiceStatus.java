package com.metasoft.ibilling.model;

public enum InvoiceStatus {

	active(0, "ใช้งาน"),
	cancel(1, "ยกเลิก");

	private int id;
	private String name;

	private InvoiceStatus(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static InvoiceStatus getById(int id) {
		for (InvoiceStatus e : values()) {
			if (e.getId() == id)
				return e;
		}
		return null;
	}
}
