package io.xiaper.sse.model;

import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.ReplayProcessor;

/**
 * @author bytedesk.com on 2019/2/9
 */
public class ChatRoomEntry {

    private ReplayProcessor<ServerSentEvent<ChatMessageEntry>> replayProcessor =
            ReplayProcessor.<ServerSentEvent<ChatMessageEntry>>create(100);

    @Override
    public void onPostMessage(ChatMessageEntry msg) {
        ServerSentEvent<ChatMessageEntry> event = ServerSentEvent.builder(msg)
                .event("chat")
                .id(Integer.toString(msg.id)).build();
        replayProcessor.onNext(event);
    }

    public Flux<ServerSentEvent<ChatMessageEntry>> subscribe(String lastEventId) {
        Integer lastId = (lastEventId != null)? Integer.parseInt(lastEventId) : null;
        return replayProcessor.filter(x -> lastId == null || x.data().get().id > lastId);
    }

}
