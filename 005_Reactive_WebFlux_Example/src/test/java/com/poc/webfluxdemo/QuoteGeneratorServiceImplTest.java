package com.poc.webfluxdemo;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;

import com.poc.webfluxdemo.model.Quote;
import com.poc.webfluxdemo.service.QuoteGeneratorServiceImpl;

import reactor.core.publisher.Flux;

public class QuoteGeneratorServiceImplTest {

	private QuoteGeneratorServiceImpl quoteGeneratorService = new QuoteGeneratorServiceImpl();

	@Before
	public void setUp() {
	}

	@Test
	public void fetchQuoteStream() throws InterruptedException {

		//get quoteFlux of quotes
		Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(1L));

		quoteFlux.take(22000).subscribe(System.out::println);
		Thread.sleep(1000L);
	}

	@Test
	public void fetchQuoteStreamCountDown() throws Exception {

		//get quoteFlux of quotes
		Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100L));

		//subscriber lambda
		Consumer<Quote> println = System.out::println;

		//error handler
		Consumer<Throwable> errorHandler = e -> System.out.println("Some Error Occurred");

		//set Countdown latch to 1
		CountDownLatch countDownLatch = new CountDownLatch(1);

		//runnable called upon complete, count down latch
		Runnable allDone = () -> countDownLatch.countDown();

		quoteFlux.take(30).subscribe(println, errorHandler, allDone);

		countDownLatch.await();
	}

}
