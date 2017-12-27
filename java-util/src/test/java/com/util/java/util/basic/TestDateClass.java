package com.util.java.util.basic;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestDateClass {
    String name;
    LocalDateTime testDateTime;


    @Override
    public String toString() {
        return "TestDateClass{" +
                "name='" + name + '\'' +
                ", testDateTime=" + testDateTime +
                '}';
    }
}
