package com.rit.strategypattern;

public class Manager extends Employee{

	public Manager() {
		setStrategyType(new ManagerStrategy());
	}
	
	@Override
	public String getRole() {
		return "Manager";
	}

}
