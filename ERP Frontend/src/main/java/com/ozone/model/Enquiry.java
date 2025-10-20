package com.ozone.model;

public class Enquiry {
	private String name;
	private String email;
	private String sub;
	private String des;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Override
	public String toString() {
		return "Enquiry [name=" + name + ", email=" + email + ", sub=" + sub + ", des=" + des + "]";
	}

}
