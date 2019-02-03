package com.xiaper.websocket.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

/**
 * @author bytedesk.com on 2019/2/3
 */
@Component
public class BinaryMessageHandler extends BinaryWebSocketHandler {

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message)
            throws Exception {

    }

}
