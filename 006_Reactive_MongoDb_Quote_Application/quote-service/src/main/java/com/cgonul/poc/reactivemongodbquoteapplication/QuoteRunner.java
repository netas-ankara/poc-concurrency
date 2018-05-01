package com.cgonul.poc.reactivemongodbquoteapplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cgonul.poc.reactivemongodbquoteapplication.client.StockQuoteClient;
import com.cgonul.poc.reactivemongodbquoteapplication.domain.Quote;

import reactor.core.publisher.Flux;

@Component
public class QuoteRunner implements CommandLineRunner {

	private final StockQuoteClient stockQuoteClient;

	public QuoteRunner(StockQuoteClient stockQuoteClient) {
		this.stockQuoteClient = stockQuoteClient;
	}

	@Override
	public void run(String... args) {
		Flux<Quote> quoteFlux = stockQuoteClient.getQuoteStream();

		quoteFlux.subscribe(System.out::println);
	}
}