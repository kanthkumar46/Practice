package com.rit.java8;

import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface Exectuable {
	public int execute();
}

interface Fly {
	default public void takeOff() {System.out.println("Fly::takeOff");}
	default public void land() {System.out.println("Fly::land");}
	default public void turn() {System.out.println("Fly::turn");}
}

interface FastFly extends Fly{
	default public void land() {System.out.println("FastFly::land");}
}

interface Sail{
	default public void turn() {System.out.println("Sail::turn");}
}

class Runner implements Fly, FastFly, Sail{
	public void run(Exectuable e){
		System.out.println(e.execute());
	}
	
	public void takeOff(){
		System.out.println("Runner::takeOff");
	}

	@Override
	public void turn() {
		Sail.super.turn();
	}
}

public class App {
	public static void main(String[] args) {
		Runner runner = new Runner();
		runner.run(()->8);
		
		List<Integer> list = Arrays.asList(1,2,3,4,5,6);
		list.forEach((i)->System.out.print(i+" "));
		int sum = list.stream()
			.map(i -> i *2)
			.reduce(0, (r,i) -> r+i);
		System.out.println(sum);
		
		runner.takeOff();
		runner.land();
		runner.turn();
	}
}
