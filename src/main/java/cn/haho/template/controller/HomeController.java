package cn.haho.template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * 欢迎页面
     * 
     * @return
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }

}
