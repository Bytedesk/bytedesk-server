package io.xiaper.sse.controller;

import io.netty.util.internal.ThreadLocalRandom;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.time.LocalTime;

/**
 * @author bytedesk.com on 2019/2/11
 */
@RestController
public class HelloController {


    /**
     * http://localhost:7001/hello
     *
     * @return
     */
    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Welcome to reactive world ~");
    }


    /**
     * server send event(sse)
     *
     * http://localhost:7001/random
     *
     * @return
     */
    @GetMapping("/random")
    public Flux<ServerSentEvent<Integer>> randomNumbers() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
                .map(data -> ServerSentEvent.<Integer>builder()
                        .event("random")
                        .id(Long.toString(data.getT1()))
                        .data(data.getT2())
                        .build());
    }


    /**
     * server send event(sse)
     * https://www.baeldung.com/spring-server-sent-events
     *
     * http://localhost:7001/stream-flux
     *
     * @return
     */
    @GetMapping(path = "/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamFlux() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "Flux - " + LocalTime.now().toString());
    }


    /**
     * server send event(sse)
     * https://www.baeldung.com/spring-server-sent-events
     *
     * http://localhost:7001/stream-sse
     *
     * @return
     */
    @GetMapping("/stream-sse")
    public Flux<ServerSentEvent<String>> streamEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<String> builder()
                        .id(String.valueOf(sequence))
                        .event("periodic-event")
                        .data("SSE - " + LocalTime.now().toString())
                        .build());
    }


}
