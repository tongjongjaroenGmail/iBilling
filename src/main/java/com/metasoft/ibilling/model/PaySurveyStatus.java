package com.metasoft.ibilling.model;

public enum PaySurveyStatus {

	active(0, "ใช้งาน"),
	cancel(1, "ยกเลิก");

	private int id;
	private String name;

	private PaySurveyStatus(int id, String name) {
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

	public static PaySurveyStatus getById(int id) {
		for (PaySurveyStatus e : values()) {
			if (e.getId() == id)
				return e;
		}
		return null;
	}
}
