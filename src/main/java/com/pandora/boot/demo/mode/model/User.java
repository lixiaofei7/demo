package com.pandora.boot.demo.mode.model;

public class User implements UserInterFace{
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void say(){
        System.out.println(this.userName+"在说话");
    }
}
