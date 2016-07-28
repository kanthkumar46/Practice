package com.rit.strategypattern;

public interface Strategy {
	public void peformStrategy();
}

class ManagerStrategy implements Strategy{

	@Override
	public void peformStrategy() {
		System.out.println("I try to manage and miminize the work resource");
	}
	
}

class DeveloperStrategy implements Strategy{

	@Override
	public void peformStrategy() {
		System.out.println("I try to write good code indeed !");
	}
	
}