package com.poc.fp.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class FunctionalProgrammingExamples {

	@Test
	public void lambdaExpression() {
		Thread t1 = new Thread(() -> System.out.println("Silence of the Lambdas"));
		t1.start();
		System.out.println("In Main Test");
	}

	@Test
	public void listIteratorLessCeremonyExternalIter() {
		List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");

		for(String doggy : dogs){
			System.out.println(doggy);
		}
	}

	@Test
	public void listInternalIterLambdaMethodTypeInferenceJustOne() {
		List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
		//drop () if we have one, still need for none or more than one parameter
		dogs.forEach(System.out::println); //inferred by compiler
	}

	@Test
	public void countDogsWithSixCharactersImp() {
		List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
		int dogCount = 0;
		for(String dog : dogs){
			if(dog.length() == 6){
				dogCount++;
			}
		}
		System.out.println(dogCount);
	}

	@Test
	public void countDogsWithEightCharactersDec() {
		List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
		System.out.println(dogs.stream()
				.filter(dog -> dog.length() == 6)
				.collect(Collectors.toList())
				.size()
		);
	}
}
