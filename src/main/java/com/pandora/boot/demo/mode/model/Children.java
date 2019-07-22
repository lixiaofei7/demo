package com.pandora.boot.demo.mode.model;

public class Children extends  Parent {
    @Override
    protected void step1() {
        System.out.println("step1");
    }

    @Override
    protected void step2() {
        System.out.println("step2");
    }

    @Override
    protected void step3() {
        System.out.println("step3");
    }

    @Override
    protected  boolean hasStep2(){
        return false;
    }

    public static void main(String[] args) {
        Parent p = new Children();
        p.run();
    }
}
