package com.rit.strategypattern;

public class Developer extends Employee{

	public Developer() {
		setStrategyType(new DeveloperStrategy());
	}
	
	@Override
	public String getRole() {
		return "Developer";
	}

}
