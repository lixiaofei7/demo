package com.pandora.boot.demo.mode.observe;

import java.util.Observable;
import java.util.Observer;

public class UserObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("观察到："+arg);
    }

    public static void main(String[] args) {
        UserObserver observer = new UserObserver();
        User user = new User();
        user.addObserver(observer);
        user.say();
    }
}
