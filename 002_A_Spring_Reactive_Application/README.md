# A Netflux example 

The only interesting thing about the poc piece is this:

```
@Override
public Flux<MovieEvent> events(String movieId) {
    return Flux.<MovieEvent>generate(movieEventSynchronousSink -> movieEventSynchronousSink.next(new MovieEvent(movieId, new Date())))
            .delayElements(Duration.ofMillis(100));
}
```

