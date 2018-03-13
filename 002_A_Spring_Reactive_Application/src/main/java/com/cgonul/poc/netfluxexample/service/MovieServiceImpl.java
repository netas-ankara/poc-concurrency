package com.cgonul.poc.netfluxexample.service;

import java.time.Duration;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.cgonul.poc.netfluxexample.domain.Movie;
import com.cgonul.poc.netfluxexample.domain.MovieEvent;
import com.cgonul.poc.netfluxexample.repository.MovieRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieServiceImpl implements MovieService {

	private final MovieRepository movieRepository;

	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public Flux<MovieEvent> events(String movieId) {
		return Flux.<MovieEvent>generate(sink -> sink.next(new MovieEvent(movieId, new Date())))
				.delayElements(Duration.ofMillis(1000))
				.take(10);
	}

	@Override
	public Mono<Movie> getMovieById(String id) {
		return movieRepository.findById(id);
	}

	@Override
	public Flux<Movie> getAllMovies() {
		return movieRepository.findAll();
	}
}
