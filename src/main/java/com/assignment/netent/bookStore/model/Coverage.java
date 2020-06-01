package com.assignment.netent.bookStore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coverage {
	private String title;
	private String body;

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
	
	public boolean isPresent(String serachParam) {
		return this.getBody().contains(serachParam) || this.getTitle().contains(serachParam);
	}
}
