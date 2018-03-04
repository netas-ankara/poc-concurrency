package com.cgonul.poc.netfluxexample.service;

import com.cgonul.poc.netfluxexample.domain.Movie;
import com.cgonul.poc.netfluxexample.domain.MovieEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

	/**
	 * Generate stream of movie events for given movie id
	 *
	 * @param movieId
	 * @return
	 */
	Flux<MovieEvent> events(String movieId);

	/**
	 * Get Movie by id
	 *
	 * @param id
	 * @return
	 */
	Mono<Movie> getMovieById(String id);

	/**
	 * Return list of all movies
	 *
	 * @return
	 */
	Flux<Movie> getAllMovies();
}
