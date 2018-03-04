package com.cgonul.poc.netfluxexample.bootstrap;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cgonul.poc.netfluxexample.domain.Movie;
import com.cgonul.poc.netfluxexample.repository.MovieRepository;

import reactor.core.publisher.Flux;

@Component
public class BootStrapCLR implements CommandLineRunner {

	private final MovieRepository movieRepository;

	public BootStrapCLR(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public void run(String... args) {
		movieRepository.deleteAll()
				.thenMany(Flux.just("Silence of the Lambdas", "AEon Flux", "Enter the Mono<Void>", "The Fluxxinator", "Back to the Future", "Meet the Fluxes", "Lord of the Fluxes")
						.map(title -> new Movie(title, UUID.randomUUID().toString()))
						.flatMap(movieRepository::save))
				.subscribe(null, null, () -> {
					movieRepository.findAll().subscribe(movie -> System.out.println(movie.toString()));
				});

	}
}
