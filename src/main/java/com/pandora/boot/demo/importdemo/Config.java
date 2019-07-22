package com.pandora.boot.demo.importdemo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//@Configuration
//@Import(value = UserServiceImpl.class)
//@Import(value = UserServiceBeanDefinitionRegistrar.class)
@EnableUserService(name="test")
@ConditionalOnBean(My.class)
public class Config {

}
