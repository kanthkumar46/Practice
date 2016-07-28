package com.rit.java8;

import java.util.Optional;

@FunctionalInterface
interface Operation {
	public double calculate(int e);
	
	default double sqrt(int e){
		return Math.sqrt(e);
	}
}

class Worker {
	int _instanceVbl;
	static int _staticVbl;
	
	public void doWork() {
		
		Operation op = (e)->e*3;
		System.out.println(op.calculate(3));
		
		op = App1::test;
		System.out.println(op.calculate(4));
		
		int i = 2;
		op = (e)->e*i;
		/*
		 *  i = 3; does not work --> Local variables accessed in lambda expression must be 
		 *  final or effectively final.
		 */
		
		// Can do both read and write on instance variable and static variable inside lambda
		op = (e)->{
			_instanceVbl = 5;
			return e*_instanceVbl;};
			
		op = (e)->{
				_staticVbl = 5;
				return e*_staticVbl;};
				
		
	}
	
}

public class App1{
	public static double test(int a){
		System.out.println("test");
		return Math.log(a);
	}
	
	public static void main(String[] args) {
		
		//default methods cannot be accessed within lambda expression
		Operation op = new Operation() {
			@Override
			public double calculate(int e) {
				return sqrt(e) * 2;
			}
		};
		System.out.println(op.calculate(2));
		
		Worker worker = new Worker();
		worker.doWork();
		
		//Avoiding Null checks 
		Optional.of(new Outer())
				.map(Outer::getNested)
				.map(Nested::getInner)
				.map(Inner::getFoo)
				.ifPresent(System.out::println);
	}
}


class Outer {
    Nested nested;
    Nested getNested() {
        return nested;
    }
}
class Nested {
    Inner inner;
    Inner getInner() {
        return inner;
    }
}
class Inner {
    String foo;
    String getFoo() {
        return foo;
    }
}
