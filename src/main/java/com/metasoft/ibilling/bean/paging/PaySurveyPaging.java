package com.metasoft.ibilling.bean.paging;

import java.util.List;

import com.metasoft.ibilling.model.PaySurvey;

public class PaySurveyPaging extends AbstractPaging<PaySurvey>{
	private List<Object[]> dataObjects;

	public List<Object[]> getDataObjects() {
		return dataObjects;
	}

	public void setDataObjects(List<Object[]> dataObjects) {
		this.dataObjects = dataObjects;
	}
	
}
