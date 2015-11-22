package com.metasoft.ibilling.model;

public enum DispatchType {

	w4(0, "ว.4"),
	meet(1, "นัดหมาย");

	private int id;
	private String name;

	private DispatchType(int id, String name) {
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

	public static DispatchType getById(int id) {
		for (DispatchType e : values()) {
			if (e.getId() == id)
				return e;
		}
		return null;
	}
}
