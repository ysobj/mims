package com.ysobj.mims.core;

import com.fasterxml.jackson.annotation.JsonProperty;


public class People {
	private Long peopleId;
	private String fullName;
	private String jobTitle;
	@JsonProperty
	public Long getPeopleId() {
		return peopleId;
	}
	public void setPeopleId(Long peopleId) {
		this.peopleId = peopleId;
	}
	@JsonProperty
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@JsonProperty
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
}
