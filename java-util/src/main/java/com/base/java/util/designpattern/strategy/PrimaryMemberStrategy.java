package com.base.java.util.designpattern.strategy;

public class PrimaryMemberStrategy implements MemberStrategy  {
    @Override
    public double calcPrice(double bookPrice) {
        System.err.println("对于初级会员没有折扣!!!");
        return bookPrice;
    }
}
