package com.pandora.boot.demo.mode.decorator;

public class HelloDecorator extends Decorator {

    public HelloDecorator(IHello iHello) {
        super(iHello);
    }

    public void d(){
        System.out.println("装饰");
    }

    @Override
    public void say(){
        d();
        super.say();
    }

    public static void main(String[] args) {
        IHello hello = new Hello();
        hello = new HelloDecorator(hello);
        hello.say();
    }
}
