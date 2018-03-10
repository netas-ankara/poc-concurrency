package com.lightbend.akka.sample;

import com.lightbend.akka.sample.Printer.Greeting;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class Greeter extends AbstractActor {

	static public Props props(String message, ActorRef printerActor) {
		return Props.create(Greeter.class, () -> new Greeter(message, printerActor));
	}

	static class WhoToGreet {

		final String who;

		WhoToGreet(String who) {
			this.who = who;
		}
	}

	static class Greet {

		Greet() {
		}
	}

	private final String message;
	private final ActorRef printerActor;
	private String greeting = "";

	//private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

	private Greeter(String message, ActorRef printerActor) {
		this.message = message;
		this.printerActor = printerActor;
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(WhoToGreet.class, wtg -> {
			this.greeting = message + ", " + wtg.who;
			//log.info("WhoToGreet message received..." + this.greeting);
		}).match(Greet.class, x -> {

			//log.info("Greet message received...");
			//After receiving a greet tell printer actor to write the greeting message
			printerActor.tell(new Greeting(greeting), getSelf());

		}).match(MakeAFace.class, makeAFace -> {
			//log.info("Make a face message received...");
			printerActor.tell(new Greeting(this.greeting + " : " + makeAFace.getFace()), getSelf());
		}).build();
	}

}

