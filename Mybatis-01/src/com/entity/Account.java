package com.entity;

public class Account {
	private Integer id;
	private String userName;
	private Integer money;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", userName=" + userName + ", money=" + money + "]";
	}
	
	
}
