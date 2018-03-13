package com.poc.basics;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class BasicChaining {

	public static void main(String[] args) throws InterruptedException {
		Flowable.fromCallable(() -> {
			Thread.sleep(1000);
			return "Done";
		}).subscribeOn(Schedulers.io()).observeOn(Schedulers.single()).subscribe(System.out::println, Throwable::printStackTrace);

		Thread.sleep(3000);
	}
}
