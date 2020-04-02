package com.capgemini.model;

public class Passenger {
	private String passangerName;
	private String userName;
	private static int passengerId=100;
	private int age;
	private char gender;
	private String password;
	public Passenger(String passangerName, String userName, int age, char gender, String password) {
		super();
		this.passangerName = passangerName;
		this.userName = userName;
		this.passengerId = passengerId+1;
		this.age = age;
		this.gender = gender;
		this.password = password;
	}
	public String getPassangerName() {
		return passangerName;
	}
	public void setPassangerName(String passangerName) {
		this.passangerName = passangerName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Passenger [passangerName=" + passangerName + ", passengerId=" + passengerId + ", age=" + age
				+ ", gender=" + gender + "]";
	}
	
	
}
