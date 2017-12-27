package com.util.java.util.rabbitmq;

public class Calculate {
    public static void main(String[] args) {
        int x1,x2,x3,x4;


        /**
         * a1 + a2 = 8
         * +    +
         * a3 - a4 = 6
         * ||   ||
         * 13   8
         */
//        for(int a1 = 0; a1 <=8; a1 ++) {
//            for(int a2 = 0; a2 <=8; a2 ++) {
//                if(a1 + a2 == 8) {
//
//                }
//
//                for(int a3 = 0; a3 <=13; a3++) {
//                    for(int a4 = 0; a4 <=8; a4++) {
//                        System.err.println(String.format("a1:[%s], a2:[%s], a3:[%s], a4:[%s]", a1,a2,a3,a4));
//                        if((a1 + a2 == 8) && (a1 + a3 == 13) && (a2 + a4 == 8) && (a3 - a4 == 6)) {
//                            System.err.println(String.format("a1:[%s], a2:[%s], a3:[%s], a4:[%s]", a1,a2,a3,a4));
//                        }
//                    }
//                }
//            }
//        }

        for(double a1 = 0; a1 <=10; a1 +=0.1) {
            for(double a2= 0; a2 <=10; a2 +=0.1) {
                for(double a3 = 0; a3 <=10; a3+= 0.1) {
                    for(double a4 =0; a4 <=10; a4+=0.1) {
                        //System.err.println(String.format("a1:[%s], a2:[%s], a3:[%s], a4:[%s]", a1,a2,a3,a4));
                        if((a1 + a2 == 8) && (a1 + a3 == 13) && (a2 + a4 == 8) && (a3 - a4 == 6)) {
                            System.err.println(String.format("a1:[%s], a2:[%s], a3:[%s], a4:[%s]", a1,a2,a3,a4));
                        }
                    }
                }
            }
        }
    }
}
