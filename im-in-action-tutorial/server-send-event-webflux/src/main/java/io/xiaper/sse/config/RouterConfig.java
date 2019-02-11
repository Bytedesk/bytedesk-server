package io.xiaper.sse.config;

import io.xiaper.sse.handler.TimeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author bytedesk.com on 2019/2/11
 */
@Configuration
public class RouterConfig {

    @Autowired
    private TimeHandler timeHandler;

    /**
     * https://blog.csdn.net/get_set/article/details/79480233
     *
     * http://localhost:7001/date
     * http://localhost:7001/time
     * http://localhost:7001/times
     *
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> timerRouter() {
        return route(GET("/time"), req -> timeHandler.getTime(req))
                .andRoute(GET("/date"), timeHandler::getDate)
                .andRoute(GET("/times"), timeHandler::sendTimePerSec);
    }
}
