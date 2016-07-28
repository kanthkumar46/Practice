package com.rit.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {
	public static boolean isEven(int i){
		return i%2 == 0;
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(2,5,1,3,6,8,7,4);
		list.stream()
			.filter(Streams::isEven)
			.sorted()
			.map(Integer::floatValue)
			.forEach(System.out::println);
		
		//stream can be created like using of method on String Class
		Stream<Integer> testStream = Stream.of(1,2,3,4,5);
		
		//can work like for loop
		IntStream.range(0, 5).forEach((i)->System.out.println(i));
		IntStream iStream = IntStream.of(1,2,3,4,5);
		
		//Special functions on primitive streams (int, double, long)
		iStream.sum();
		iStream = IntStream.of(1,2,3,4,5);
		iStream.average();
		
		//primitive stream to Object stream and back to primitive stream
		iStream = IntStream.of(1,2,3,4,5);
		iStream.mapToObj((i)->i+"str")
			   .map((str)->str.substring(0, 1))
			   .mapToDouble(Double::valueOf)
			   .forEach(System.out::println);
		
		
		//will return as soon as the match is found without executing further 
		testStream.map((i)->i*3)
				  .anyMatch((i)->{
					  System.out.println("inside Anymatch");
					  return i%2==0;
				  });
		
		//Why order of chaining function matters 
		System.out.println("\nToo many operations map is called every time");
		iStream = IntStream.of(1,2,3,4,5);
		iStream.map((i)->{
					System.out.println("inside map");
					return i * 3;
			   })
			   .filter((i)->{
				   	System.out.println("inside filter");
					return i%2==0;
			   })
			   .forEach(System.out::println);
		
		System.out.println("\nOperations are reduced map is called only twice");
		iStream = IntStream.of(1,2,3,4,5);
		iStream.filter((i)->{
					System.out.println("inside filter");
					return i%2==0;
			   })
			   .map((i)->{
					System.out.println("inside map");
					return i * 3;
			   })
			   .forEach(System.out::println);
		
		/*
		 * Streams are closed as soon as the terminal operation is called on them
		 * We can overcome the limitation using supplier of stream
		 */
		Supplier<Stream<Integer>> streamSupplier = ()->Stream.of(1,2,3,4,5,8,9,15);
		System.out.println(streamSupplier.get().count());
		System.out.println(streamSupplier.get().count()); //still works fine without exception
		
		//Collectors
		testStream = streamSupplier.get();
		Predicate<Integer> evenPredicate = Streams::isEven;
		List<Integer> testList = testStream.filter(evenPredicate.negate())
				  						   .collect(Collectors.toList());
		
		testStream = streamSupplier.get();
		String listAsString = testStream.filter(evenPredicate.negate())
										 .map(i->Integer.toString(i))
				   						 .collect(Collectors.joining());
		System.out.println(listAsString);
		
		testStream = streamSupplier.get();
		System.out.println(testStream.map(i->i.intValue())
				  .collect(Collectors.groupingBy(i->i%2==0, Collectors.counting())));
		
		testStream = streamSupplier.get();
		SortedSet<Integer> sortedSet = 
				testStream.filter(evenPredicate.negate())
				  		  .collect(Collectors.toCollection(TreeSet::new));
		
		testStream = streamSupplier.get();
		Map<Integer, Integer> map = 
				testStream.collect(Collectors.toMap(Function.identity(), i->i+1));
		System.out.println(map);
		
		//in case key conflict occurs in previous toMap function 
		//an exception will be thrown can be avoided using merge function
		testStream = Stream.of(1,2,3,1,4,2);
		map = testStream.
				collect(Collectors.toMap(Function.identity(), i->i+1, (oldVal, newVal)->oldVal+newVal));
		System.out.println(map);
		
		//FlatMap, Flattens multiple streams to single stream
		Stream.of("XML", "Java",  "CSS")
        .flatMap(name->name.chars().mapToObj(c->(char)c))
        .forEach(System.out::println);
		
		//Converting result of collector using andThen
		testStream = streamSupplier.get();
		System.out.println(testStream.filter(Streams::isEven)
				 .collect(Collectors.collectingAndThen(Collectors.toList(), myList->myList.size())));
		
	}
}
