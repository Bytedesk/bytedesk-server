package io.xiaper.sse.repository;

import io.xiaper.sse.model.MyEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

/**
 * @author bytedesk.com on 2019/2/11
 */
public interface MyEventRepository extends ReactiveMongoRepository<MyEvent, Long> {
    @Tailable
    Flux<MyEvent> findBy();
}
