package com.pandora.boot.demo.test;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class EnumTest {

    public static void main(String[] args) {
        EnumSet<Day> set = EnumSet.allOf(Day.class);
        //System.out.print(set);

        List<String> list = new ArrayList();
        list.add("are"); list.add("where");list.add("anvato"); list.add("java"); list.add("abc");

        list.stream().filter(s -> s.startsWith("a")).sorted().limit(3).collect(Collectors.toList()).forEach(System.out::println);
       /* list.stream().filter(s -> s.startsWith("a")).sorted().limit(3)
                .collect(Collectors.toList()).forEach(System.out::println);*/

    }

    enum Day{

        M("一"),T("二"),F("三");

        private String desc;

        Day(String desc){
            this.desc = desc;
        }

        public String getDesc(){
            return this.desc;
        }
    }
}
