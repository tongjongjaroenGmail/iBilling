package com.metasoft.ibilling.model;

public enum WorkTime {

	in(0, "ใน"),
	out(1, "นอก");

	private int id;
	private String name;

	private WorkTime(int id, String name) {
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

	public static WorkTime getById(int id) {
		for (WorkTime e : values()) {
			if (e.getId() == id)
				return e;
		}
		return null;
	}
}
