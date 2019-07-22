package com.pandora.boot.demo.mode.model;

import java.util.ArrayList;
import java.util.List;

public class Clonetest implements Cloneable {

    private String name;

    private ArrayList<String> list = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(String value) {
        this.list.add(value);
    }

    @Override
    protected Clonetest clone() throws CloneNotSupportedException {
        Clonetest test = (Clonetest)super.clone();
        test.list = (ArrayList)this.list.clone();
        return test;
    }

    public static void main(String[] args) throws Exception{
        Clonetest test = new Clonetest();
        test.setList("1");
        test.setList("2");
        test.setList("3");
        test.setName("yuanshi");
        Clonetest test2 = test.clone();
        test2.setList("4");
        test2.setName("clone");
        System.out.println(test.getName());
        System.out.println(test.getList());
    }
}
