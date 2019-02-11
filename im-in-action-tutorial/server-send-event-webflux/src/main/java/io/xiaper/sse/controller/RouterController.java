package io.xiaper.sse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * @author bytedesk.com on 2019/2/11
 */
@Controller
public class RouterController {

    /**
     * 首页
     *
     * @param principal
     * @param model
     * @return view
     */
    @GetMapping("/")
    public String index(Principal principal, Model model) {
        return "index";
    }
}
