package com.base.java.util.designpattern.strategy;

public class IntermediateMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double bookPrice) {
        System.err.println("对于中级会员的价格为10%");
        return bookPrice * 0.9;
    }
}
