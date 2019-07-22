package com.pandora.boot.demo.inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("say")
    public String say(){
        return Utils.getHello();
    }
}
