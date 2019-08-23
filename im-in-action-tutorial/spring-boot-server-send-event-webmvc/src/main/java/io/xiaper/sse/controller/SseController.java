package io.xiaper.sse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * https://www.baeldung.com/spring-mvc-sse-streams
 * https://golb.hplar.ch/2017/03/Server-Sent-Events-with-Spring.html
 *
 * @author bytedesk.com on 2019/2/9
 */
@RestController
public class SseController {

    private ExecutorService nonBlockingService = Executors.newCachedThreadPool();


    @RequestMapping(value="/push",produces="text/event-stream;charset=utf-8")
    @ResponseBody
    public String push() {
        Random r = new Random();
        return "data:Testing 1,2,3" + r.nextInt() +"\n\n";
    }

//    @GetMapping("/rbe")
//    public ResponseEntity<ResponseBodyEmitter> handleRbe() {
//        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
//        nonBlockingService(() -> {
//            try {
//                emitter.send(
//                        "/rbe" + " @ " + new Date(), MediaType.TEXT_PLAIN);
//                emitter.complete();
//            } catch (Exception ex) {
//                emitter.completeWithError(ex);
//            }
//        });
//        return new ResponseEntity(emitter, HttpStatus.OK);
//    }


    /**
     *
     * @return
     */
    @GetMapping("/sse")
    public SseEmitter handleSse() {
        SseEmitter emitter = new SseEmitter();
        nonBlockingService.execute(() -> {
            try {
                emitter.send("/sse" + " @ " + new Date());
                // we could send more events
                emitter.complete();
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }


    @GetMapping("/srb")
    public ResponseEntity<StreamingResponseBody> handleRbe() {
        StreamingResponseBody stream = out -> {
            String msg = "/srb" + " @ " + new Date();
            out.write(msg.getBytes());
        };
        return new ResponseEntity(stream, HttpStatus.OK);
    }

}
