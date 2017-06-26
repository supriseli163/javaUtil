package com.base.java.util.framework.cglib;

public class Test {
    public static int[] solve(int[] digits) {
        int sumA = 0;
        int sumB = 0;

        int length = digits.length;

        for(int index = 0; index < length; index++) {
            sumA += digits[index] * square(length - index);
            sumB += digits[length - (index + 1)] * square(length - index);
        }

        int cha = sumA - sumB;
        return cal(cha);

    }

    public static int square(int size) {
        int square = 1;
        for(int i = 1; i < size; i++) {
            square = square * 10;
        }
        return square;
    }

    public static int[] cal(int input) {
        int size = String.valueOf(input).length();
        int a = input;
        int [] result = new int[size];
        for(int index = 0; index < size; index ++) {
            result[index] = a / square(size - index);
            a = a%square(size - index);
        }
       return result;
    }

    public static void main(String[] args) {
        int [] a  = new int []{3,2,1};
        int result[] = solve(a);

        for(int index = 0; index < result.length; index++) {
            String str = (index == result.length -1) ? " " : ",";
            System.out.print(index + str);
        }
    }
}
