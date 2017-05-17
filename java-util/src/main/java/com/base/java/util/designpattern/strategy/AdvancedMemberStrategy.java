package com.base.java.util.designpattern.strategy;

public class AdvancedMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double bookPrice) {
        System.err.println("对于高级会员的价格为20%");
        return bookPrice * 0.8;
    }
}
