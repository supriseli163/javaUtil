package com.base.java.util.sign.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public enum  FungType implements IEnum {
    COMMON(1),
    ECOMMERCESETTLEMENT(2);

    @Getter
    private int value;
}
