package com.metasoft.ibilling.model;

public enum ClaimTp {

	insurance(0, "ประกันภัย"),
	person(1, "บุคคล"),
	none(2, "ไม่มี");

	private int id;
	private String name;

	private ClaimTp(int id, String name) {
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

	public static ClaimTp getById(int id) {
		for (ClaimTp e : values()) {
			if (e.getId() == id)
				return e;
		}
		return null;
	}
}
