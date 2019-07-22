//package com.pandora.boot.demo.rabbit;
//
//import com.rabbitmq.client.Channel;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//public class RabbitMqController {
//
//    @GetMapping("send/{msg}")
//    public String send(@PathVariable("msg")String msg){
//        RabbitMqUtils.sendMsg(msg);
//        return msg;
//    }
//
//    @GetMapping("sub/{id}")
//    public void sub(@PathVariable("id")String id){
//        Channel channel = RabbitMqUtils.sub(id);
//        RabbitMqUtils.consumerMsg(channel,id);
//    }
//}
