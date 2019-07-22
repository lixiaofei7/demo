package com.pandora.boot.demo.inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Utils {

    @Autowired
    IService iservice;

    private static Utils utils;

    @PostConstruct
    public void after(){
        utils = this;
    }

    public static String getHello(){
        return utils.iservice.hello();
    }
}
