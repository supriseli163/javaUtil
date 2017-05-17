package com.base.java.util.designpattern.strategy;

public interface MemberStrategy {
    /**
     * 计算图书的价格
     * @param bookPrice 图书的原价
     * @return  计算打折后的价格
     */
    public double calcPrice(double bookPrice);
}
