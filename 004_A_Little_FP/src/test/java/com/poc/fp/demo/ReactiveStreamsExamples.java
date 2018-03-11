package com.poc.fp.demo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.junit.Test;

import reactor.core.publisher.Flux;

public class ReactiveStreamsExamples {

	@Test
	public void simpleStreamExample() {
		Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
		dogs.toStream().forEach(System.out::println);
	}

	@Test
	public void simpleStreamExample2() {
		Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
		dogs.subscribe(System.out::println);
	}

	@Test
	public void simpleStreamExample3() {
		Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
		//We need a subscribe function.
		dogs.doOnEach(dog -> System.out.println(dog.get()));
	}

	@Test
	public void simpleStreamExample4() {
		Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");

		//trigger subscription
		dogs.doOnEach(dog -> System.out.println(dog.get())).subscribe();
	}

	@Test
	public void simpleStreamExample5WithSubscriber() {
		Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");

		//trigger subscription
		dogs.subscribe((System.out::println), null, (() -> {
			System.out.println("Woot! all Done");
		}));
	}

	@Test
	public void simpleStreamExample5WithSubscriberConsumers() {
		Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");

		//subscriber lambda
		Consumer<String> println = System.out::println;

		//error handler
		Consumer<Throwable> errorHandler = e -> System.out.println("Some Error Occurred");

		//runnable upon complete
		Runnable allDone = () -> System.out.println("Woot! All Done!");

		//trigger subscription
		dogs.subscribe(println, errorHandler, allDone);
	}

	@Test
	public void mapStreamExample() {
		Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");

		dogs.map(String::length).doOnEach(System.out::println).subscribe();
	}

	@Test
	public void filterStreamExample() {
		Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
		dogs.filter(s -> s.length() == 6).subscribe(System.out::println);
	}

	@Test
	public void filterAndLimitStreamExample() {
		Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
		dogs.filter(s -> s.length() == 6).take(2).subscribe(System.out::println);
	}

	@Test
	public void filterAndSortStreamExample() {
		Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
		dogs.filter(s -> s.length() == 6).take(2).sort().subscribe(System.out::println);
	}

	@Test
	public void filterAndSortStreamWithCollectorExample() {
		Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");

		dogs.filter(s -> s.length() == 6).take(3)
				.sort().collect(Collectors.joining(", "))
				.subscribe(System.out::println);
	}

	@Test
	public void testFlatMap() {
		Flux<List<List<Integer>>> listFlux = Flux.just(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)));

		listFlux.flatMap(Flux::fromIterable).flatMap(Flux::fromIterable).subscribe(System.out::println);
	}

	@Test
	public void testFlatMap2() {
		Flux<List<List<Integer>>> listFlux = Flux.just(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)));
		listFlux.flatMap(lists -> Flux.fromIterable((lists.stream().flatMap(Collection::stream)).collect(Collectors.toList()))).subscribe(System.out::println);
	}

	@Test
	public void testReduction(){
		Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Poodle", "Yorkie", "Mutt");
		dogs.reduce((a, b) -> a + " - " + b).subscribe(System.out::println);
	}
}
