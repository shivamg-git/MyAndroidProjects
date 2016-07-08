package com.example.spider.derek2;

import java.io.Serializable;

/**
 * Created by spider on 30/6/16.
 */
public class Human implements Serializable {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;

}
