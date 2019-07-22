package com.pandora.boot.demo.importdemo;

public class UserServiceImpl implements  UserService {


    @Override
    public int save(User user) {
        System.out.println("调用了当前方法");
        return 1;
    }

}
