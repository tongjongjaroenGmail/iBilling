package com.metasoft.ibilling.model;

public enum ClaimStatus {
	open(0, "จ่ายงาน"),
	waitCheck(1, "รอการตรวจสอบ"),
	closeCheck(2, "ปิดการตรวจสอบ"),
	approve(3, "สินไหมอนุมัติ"),
	cancel(4, "ยกเลิก");

	private int id;
	private String name;

	private ClaimStatus(int id, String name) {
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

	public static ClaimStatus getById(int id) {
		for (ClaimStatus e : values()) {
			if (e.getId() == id)
				return e;
		}
		return null;
	}
}
