package io.xiaper.sse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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



}
