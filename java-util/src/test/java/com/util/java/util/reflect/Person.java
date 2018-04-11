package com.util.java.util.reflect;

import lombok.Data;

@Data
public class Person {
    private String name;
    private String mobile;

    private Person() {
        this.name = "zhangsan";
        this.mobile = "18818180919";
    }

    public Person(String username, String mobile) {
        this.name = username;
        this.mobile = mobile;
    }

    public void printUserInfo() {
        System.err.println(String.format("User info is username:[%s], mobile:[%s]", name, mobile));
    }
}
