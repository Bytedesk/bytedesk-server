package com.xiaper.websocket.config;

import com.xiaper.websocket.handler.TextMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#websocket-server
 *
 * @author xiaper.com
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(textMessageHandler(), "/user");
	}

	@Bean
	public TextMessageHandler textMessageHandler() {
		return new TextMessageHandler();
	}
}