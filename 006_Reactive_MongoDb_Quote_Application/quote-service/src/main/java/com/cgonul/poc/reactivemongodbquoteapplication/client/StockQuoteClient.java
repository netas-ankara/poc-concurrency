package com.cgonul.poc.reactivemongodbquoteapplication.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.cgonul.poc.reactivemongodbquoteapplication.domain.Quote;

import lombok.Setter;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Setter
@Component
public class StockQuoteClient {

	@Value ("${quote.service.host}")
	private String host;

	@Value ("${quote.service.port}")
	private String port;

	@Value ("${quote.service.path}")
	private String path;

	public Flux<Quote> getQuoteStream() {
		String url = String.format("http://%s:%s", host, port);
		log.debug("Url is set to : " + url);
		return WebClient.builder()
						.baseUrl(url)
						.build()
						.get()
						.uri(path)
						.accept(MediaType.APPLICATION_STREAM_JSON)
						.retrieve()
						.bodyToFlux(Quote.class);
	}

}
