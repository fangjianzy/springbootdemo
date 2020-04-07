package com.fangjian.stream;

import java.math.BigDecimal;

public class StudentsDTO {
	private int sId;
	private String sName;
	private int tId;
	private int age;
	private BigDecimal money;
	private String tName;
	
	
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public int gettId() {
		return tId;
	}
	public void settId(int tId) {
		this.tId = tId;
	}
	
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public StudentsDTO(int sId, String sName, int tId) {
		super();
		this.sId = sId;
		this.sName = sName;
		this.tId = tId;
	}
	@Override
	public String toString() {
		return "StudentsDTO [sId=" + sId + ", sName=" + sName + ", tId=" + tId + ", tName=" + tName + ", gettName()="
				+ gettName() + ", getsId()=" + getsId() + ", getsName()=" + getsName() + ", gettId()=" + gettId()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
}
