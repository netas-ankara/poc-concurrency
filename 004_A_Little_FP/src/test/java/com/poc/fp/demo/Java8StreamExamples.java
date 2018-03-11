package com.poc.fp.demo;

import java.util.Arrays;
import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

public class Java8StreamExamples {

	@Test
	public void simpleStreamExample() {
		List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");

		dogs.forEach(System.out::println);
	}

	@Test
	public void parallelStreamExample() {
		List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");

		dogs.parallelStream().forEach(System.out::println);
	}

	@Test
	public void mapStreamExample() {
		List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
		dogs.stream().map(String::length).forEach(System.out::println);
	}

	@Test
	public void filterStreamExample() {
		List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
		dogs.stream().filter(s -> s.length() == 6).forEach(System.out::println);
	}

	@Test
	public void filterAndLimitStreamExample() {
		List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
		dogs.stream().filter(s -> s.length() == 6).limit(2).forEach(System.out::println);
	}

	@Test
	public void filterAndSortStreamExample() {
		List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
		dogs.stream().filter(s -> s.length() == 6).sorted().forEach(System.out::println);
	}

	@Test
	public void filterAndSortStreamWithCollectorExample() {
		List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
		String dogString = dogs.stream().filter(s -> s.length() == 6).sorted().collect(Collectors.joining(", "));
		System.out.println(dogString);
	}

	@Test
	public void filterAndSortStreamWithFunctionalComp() {
		List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
		String dogString = dogs.stream().filter(s -> s.length() == 6).limit(3).map(String::toUpperCase).sorted().collect(Collectors.joining(", "));
		System.out.println(dogString);
	}

	@Test
	public void testStats() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		IntSummaryStatistics statistics = numbers.stream().mapToInt(x -> x) //simple operation
				.summaryStatistics();
		System.out.println(statistics.toString());
	}

	@Test
	public void testGetMax() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		OptionalInt max = numbers.stream().mapToInt(x -> x) //simple operation
				.max();
		if(max.isPresent()){
			System.out.println("Max: " + max.getAsInt());
		}
	}

	@Test
	public void testSum() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		int numbersSum = numbers.stream().mapToInt(x -> x).sum();
		System.out.println("Sum: " + numbersSum);
	}

	@Test
	public void testSumCollector() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		Integer numbersSum = numbers.stream().mapToInt(value -> value).sum();
		System.out.println("Sum: " + numbersSum);
	}

	@Test
	public void testGroupingBy() {
		List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
		Map<Integer, Set<String>> groupedMap = dogs.stream().collect(Collectors.groupingBy(String::length, Collectors.toSet()));
		groupedMap.entrySet().forEach(System.out::println);
	}

	@Test
	public void testFlatMap() {
		List<List<Integer>> listOfLists = Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6));
		List<Integer> numbers = listOfLists.stream().flatMap(Collection::stream).collect(Collectors.toList());
		numbers.forEach(System.out::println);
	}

	@Test
	public void testReduction(){
		List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
		Optional<String> result = dogs.stream().reduce((a, b) -> a + " - " + b);
		result.ifPresent(System.out::println);
	}
}
