package com.pandora.boot.demo.importdemo;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Import(UserServiceSelector.class)
public @interface EnableUserService {

    String name() default "";
}
