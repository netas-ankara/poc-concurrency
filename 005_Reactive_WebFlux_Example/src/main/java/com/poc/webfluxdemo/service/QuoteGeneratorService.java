package com.poc.webfluxdemo.service;

import java.time.Duration;

import com.poc.webfluxdemo.model.Quote;

import reactor.core.publisher.Flux;

public interface QuoteGeneratorService {

	Flux<Quote> fetchQuoteStream(Duration period);
}
