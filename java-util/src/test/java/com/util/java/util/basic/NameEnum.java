package com.util.java.util.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum  NameEnum {
    XIAO_MING(1, "ming.xiao"),
    ZHANG_SAN(2, "san.zhang"),
    LI_SI(3, "si.li");

    @Getter private int value;
    @Getter private String description;
}
