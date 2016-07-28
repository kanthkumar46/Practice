package com.rit.strategypattern;

public abstract class Employee {
	
	protected String name;
	protected int age;
	protected Strategy strategyType;
	
	public abstract String getRole();
	
	public int getAge() {
		return age;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void executeStrategy(){
		strategyType.peformStrategy();
	}
	
	public void setStrategyType(Strategy strategyType) {
		this.strategyType = strategyType;
	}
}
 