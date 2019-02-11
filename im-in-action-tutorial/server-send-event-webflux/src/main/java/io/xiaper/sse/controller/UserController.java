package io.xiaper.sse.controller;

import io.xiaper.sse.dao.UserService;
import io.xiaper.sse.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author bytedesk.com on 2019/2/11
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * localhost:7001/user
     *
     * @param user
     * @return
     */
    @PostMapping("")
    public Mono<User> save(User user) {
        return this.userService.save(user);
    }


    @DeleteMapping("/{username}")
    public Mono<Long> deleteByUsername(@PathVariable String username) {
        return this.userService.deleteByUsername(username);
    }

    @GetMapping("/{username}")
    public Mono<User> findByUsername(@PathVariable String username) {
        return this.userService.findByUsername(username);
    }

    /**
     * localhost:7001/user
     *
     * @return
     */
    @GetMapping("")
    public Flux<User> findAll() {
        return this.userService.findAll();
    }
}

