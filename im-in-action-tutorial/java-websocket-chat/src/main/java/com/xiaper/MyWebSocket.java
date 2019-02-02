package com.xiaper;

import javax.websocket.*;
import java.io.IOException;

/**
 * https://www.pegaxchange.com/2018/01/28/websocket-server-java/
 *
 * @author bytedesk.com on 2019/2/1
 */
public class MyWebSocket {

//    private static PushTimeService pst;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen::" + session.getId());
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
