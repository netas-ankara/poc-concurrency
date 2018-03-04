package com.cgonul.poc.netfluxexample.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.cgonul.poc.netfluxexample.domain.Movie;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {

}
