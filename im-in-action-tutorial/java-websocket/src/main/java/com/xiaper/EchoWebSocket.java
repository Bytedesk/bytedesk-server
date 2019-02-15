package com.xiaper;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * https://www.pegaxchange.com/2018/01/28/websocket-server-java/
 *
 * @author bytedesk.com on 2019/2/3
 */
@ServerEndpoint("/echo")
public class EchoWebSocket {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen::" + session.getId());

        /////////////////////////////////////////////////////////////////////////////
        // ws://127.0.0.1:8080/WebSocketServer/endpoint?push=TIME
        // Access request parameters from URL query String.
        // If a client subscribes, add Session to PushTimeService.
        //
        Map<String, List<String>> params = session.getRequestParameterMap();

        if (params.get("push") != null && (params.get("push").get(0).equals("TIME"))) {

            PushTimeService.initialize();
            PushTimeService.add(session);
        }
        /////////////////////////////////////////////////////////////////////////////
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose::" +  session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("onMessage::From=" + session.getId() + " Message=" + message);

        try {
            session.getBasicRemote().sendText("Hello Client " + session.getId() + "!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("onError::" + t.getMessage());
    }
}
