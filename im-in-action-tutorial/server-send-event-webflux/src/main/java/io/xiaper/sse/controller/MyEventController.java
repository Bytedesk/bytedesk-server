package io.xiaper.sse.controller;

import io.xiaper.sse.model.MyEvent;
import io.xiaper.sse.repository.MyEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author bytedesk.com on 2019/2/11
 */
@RestController
@RequestMapping("/events")
public class MyEventController {

    @Autowired
    private MyEventRepository myEventRepository;

    @PostMapping(path = "", consumes = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<Void> loadEvents(@RequestBody Flux<MyEvent> events) {
        return this.myEventRepository.insert(events).then();
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<MyEvent> getEvents() {
        return this.myEventRepository.findBy();
    }
}
