package com.pandora.boot.demo.importdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        //My my = new My();
       // My my2 = context.getBean(My.class);
       // System.out.println(my2);
        UserService userService = context.getBean(UserService.class);
        userService.save(null);
        context.close();
    }
}
