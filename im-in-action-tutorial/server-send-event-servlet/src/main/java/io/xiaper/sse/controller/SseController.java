package io.xiaper.sse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author bytedesk.com on 2019/2/9
 */
@RestController
public class SseController {

    @RequestMapping(value="/push",produces="text/event-stream;charset=utf-8")
    @ResponseBody
    public String push() {
        Random r = new Random();
        return "data:Testing 1,2,3" + r.nextInt() +"\n\n";
    }

    @RequestMapping(value = "/sseTest", method = RequestMethod.GET)
    public String getSSEView () {
        return "sseTest";
    }

}
