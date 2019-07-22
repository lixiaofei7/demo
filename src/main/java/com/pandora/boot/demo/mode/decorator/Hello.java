package com.pandora.boot.demo.mode.decorator;

public class Hello implements IHello{

    @Override
    public void say(){
        System.out.println("hello say : 你好 ");
    }
}
