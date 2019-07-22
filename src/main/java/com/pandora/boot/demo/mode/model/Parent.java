package com.pandora.boot.demo.mode.model;

public abstract class Parent {

    protected abstract   void step1();

    protected abstract void step2();

    protected abstract void step3();

    protected  boolean hasStep2(){
        return true;
    }

    final void run(){
        step1();
        if(hasStep2()){
            step2();
        }
        step3();
    }

}
