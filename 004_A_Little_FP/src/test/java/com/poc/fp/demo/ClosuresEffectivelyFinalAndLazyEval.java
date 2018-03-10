package com.poc.fp.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

public class ClosuresEffectivelyFinalAndLazyEval {

	@Test
	public void lambdaExample() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		numbers.stream().map(number -> number * 2)
				.forEach(System.out::println);
	}

	@Test
	public void closureExample() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		Integer multiplier = 2;
		numbers.stream().map(number -> number * multiplier)
				.forEach(System.out::println);
	}

	@Test
	public void closureUsingFinal(){
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		final Integer multiplier = 2;
		Stream<Integer> numberStream = numbers.stream().map(number -> number * multiplier);
		//multiplier = 3;
		numberStream.forEach(System.out::println);
	}

	@Test
	public void closureEffectivelyFinal() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

		Integer multiplier = 2; //effectively final
		Stream<Integer> numberStream = numbers.stream().map(number -> number * multiplier);
		//multiplier = 3;

		numberStream.forEach(System.out::println);
	}

	@Test
	public void breakingFinal() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		final Integer[] multiplier = {2};
		Stream<Integer> numberStream = numbers.stream().map(number -> number * multiplier[0]);
		multiplier[0] = 0;

		//Not run till consuming.
		numberStream.forEach(System.out::println);
	}
}
