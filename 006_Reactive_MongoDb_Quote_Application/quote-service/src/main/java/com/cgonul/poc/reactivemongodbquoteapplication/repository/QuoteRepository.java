package com.cgonul.poc.reactivemongodbquoteapplication.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.cgonul.poc.reactivemongodbquoteapplication.domain.Quote;

public interface QuoteRepository extends ReactiveMongoRepository<Quote, String> {

}
