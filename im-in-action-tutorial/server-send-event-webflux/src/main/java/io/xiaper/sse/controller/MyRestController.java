package io.xiaper.sse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author bytedesk.com on 2019/2/9
 */
public class MyRestController {

    private static final Logger LOG = LoggerFactory.getLogger(MyRestController.class);

    @Autowired
    private ChatHistoryService chatHistoryService;

    @GetMapping(path = "/chat/room/{chatRoom}/sseSpring5", produces = "text/event-stream")
    public Flux<ServerSentEvent<ChatMessageEntry>> subscribeChatMessages_spring5(
            @PathVariable("chatRoom") String chatRoom,
            @RequestHeader(name="last-event-id", required=false) String lastEventId
    ) {
        ChatRoomEntry chatRoomEntry = chatHistoryService.getChatRoom(chatRoom);
        if (chatRoomEntry == null) {
            return null;
        }
        LOG.info("subscribeMessagesSpring5 lastEventId:" + lastEventId);
        return chatRoomEntry.subscribe(lastEventId);
    }


}
