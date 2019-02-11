package io.xiaper.sse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author bytedesk.com on 2019/2/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "event")
public class MyEvent {

    @Id
    private Long id;
    private String message;

}
