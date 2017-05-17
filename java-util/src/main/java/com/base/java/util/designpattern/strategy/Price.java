package com.base.java.util.designpattern.strategy;

public class Price {
    //
    private MemberStrategy memberStrategy;

    public Price(MemberStrategy memberStrategy) {
        this.memberStrategy = memberStrategy;
    }

    public double quote(double bookPrice) {
        return this.memberStrategy.calcPrice(bookPrice);
    }
}
