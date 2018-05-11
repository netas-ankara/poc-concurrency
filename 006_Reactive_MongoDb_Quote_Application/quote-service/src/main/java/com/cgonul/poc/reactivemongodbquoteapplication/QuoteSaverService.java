package com.cgonul.poc.reactivemongodbquoteapplication;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.cgonul.poc.reactivemongodbquoteapplication.client.StockQuoteClient;
import com.cgonul.poc.reactivemongodbquoteapplication.domain.Quote;
import com.cgonul.poc.reactivemongodbquoteapplication.repository.QuoteRepository;

import reactor.core.publisher.Mono;

@Service
public class QuoteSaverService implements ApplicationListener<ContextRefreshedEvent> {

	private final StockQuoteClient stockQuoteClient;
	private final QuoteRepository quoteRepository;

	public QuoteSaverService(StockQuoteClient stockQuoteClient, QuoteRepository quoteRepository) {
		this.stockQuoteClient = stockQuoteClient;
		this.quoteRepository = quoteRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		stockQuoteClient.getQuoteStream()
				.log("quote-monitor-service")
				.subscribe(quote -> {
					Mono<Quote> savedQuote = quoteRepository.save(quote);

					savedQuote.subscribe(savedQuoteInner -> {
						System.out.println("I saved a quote :" + savedQuoteInner.toString());
					});
				});
	}
}
