#Hello Akka Example

1. First create an ActorSystem.
2. Create 3 instances of a Greeter Actor and one instance of a Printer actor.
3. The example then sends messages to the Greeter Actor instances, 
which store them internally. 
Finally, instruction messages to the Greeter Actors trigger them to send messages to the Printer Actor, 
which outputs them to the console


# Benefits of using the Actor Model

The following characteristics of Akka allow you to solve difficult concurrency and scalability challenges in an intuitive way:

* **Event-driven model** — Actors perform work in response to messages. Communication between Actors is asynchronous, allowing Actors to send messages and continue their own work without blocking to wait for a reply.
* **Strong isolation principles** — Unlike regular objects in Java, an Actor does not have a public API in terms of methods that you can invoke. Instead, its public API is defined through messages that the actor handles. This prevents any sharing of state between Actors; the only way to observe another actor’s state is by sending it a message asking for it.
* **Location transparency** — The system constructs Actors from a factory and returns references to the instances. Because location doesn’t matter, Actor instances can start, stop, move, and restart to scale up and down as well as recover from unexpected failures.
* **Lightweight** — Each instance consumes only a few hundred bytes, which realistically allows millions of concurrent Actors to exist in a single application.

Let’s look at some best practices for working with Actors and messages in the context of the Hello World example.