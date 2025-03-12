package com.example.spring.firebase.dto;

import java.util.HashMap;
import java.util.Map;

public class RequestDto {

	private String title;
	private String body;
	private String clickAction;

	private String category;

	private Map<String, Object> dataMap = new HashMap();

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getClickAction() {
		return clickAction;
	}

	public void setClickAction(String clickAction) {
		this.clickAction = clickAction;
	}

}
