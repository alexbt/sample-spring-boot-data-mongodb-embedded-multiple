package com.alexbt.mongodb;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class OtherModel {
	
	@Id
	private long id;
	
	private String value;
	
	private String other = "model2";
	
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
