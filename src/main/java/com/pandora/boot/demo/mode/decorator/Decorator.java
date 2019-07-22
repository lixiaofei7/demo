package com.pandora.boot.demo.mode.decorator;

public abstract class Decorator implements IHello{

    private IHello iHello;

    public Decorator(IHello iHello){
        this.iHello = iHello;
    }

    @Override
    public void say(){
        this.iHello.say();
    }
}
