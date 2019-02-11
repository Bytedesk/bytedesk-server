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
     * http://localhost:8002/date
     * http://localhost:8002/time
     *
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> timerRouter() {
        return route(GET("/time"), req -> timeHandler.getTime(req))
                .andRoute(GET("/date"), timeHandler::getDate);
    }
}
