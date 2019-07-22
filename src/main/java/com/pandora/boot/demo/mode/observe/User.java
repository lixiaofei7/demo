package com.pandora.boot.demo.mode.observe;

import java.util.Observable;

public class User extends Observable {

    public void say(){
        System.out.println("被观察者在说话");
        super.setChanged();
        super.notifyObservers("开始发言了");
    }
}
