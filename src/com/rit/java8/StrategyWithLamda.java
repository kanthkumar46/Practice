package com.rit.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class StrategyWithLamda {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,3,4,5,6);
		System.out.println(computeTotal(list, e-> true));
		System.out.println(computeTotal(list, e-> e%2 == 0));
		System.out.println(computeTotal(list, e-> e%2 != 0));
	}

	private static int computeTotal(List<Integer> list, 
			Predicate<Integer> predicate) {
		return list
				.stream()
				.filter(predicate)
				.reduce(0, (c, i) ->c+i);
	}

}
