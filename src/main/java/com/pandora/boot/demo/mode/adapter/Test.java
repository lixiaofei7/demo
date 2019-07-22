package com.pandora.boot.demo.mode.adapter;

public class Test {
    public static void main(String[] args) {
        IUser user = new User();
        user = new ManAdapter();
        user.say();
    }
}
