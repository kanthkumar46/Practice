package com.rit.strategypattern;

/**
 * Define family of algorithms see Strategy.java file, encapsulate each one
 * and make them interchangable. this patterns lets the strategy algorithm to
 * be changed dynamically (Example use case may be to perform linear or binary 
 * search depending on whether the array is sorted or not). 
 * 
 * @author KanthKumar
 *
 */
public class StrategyMain {
	public static void main(String[] args) {
		Employee dev = new Developer();
		Employee man = new Manager();
		System.out.print("Developer Strategy is : ");
		dev.executeStrategy();
		System.out.print("Managers Strategy is : ");
		man.executeStrategy();
		dev.setStrategyType(new ManagerStrategy());
		System.out.println("Guess what ? :P");
		System.out.print("Developer Strategy now is : ");
		dev.executeStrategy();
	}
}	
