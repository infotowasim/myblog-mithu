package com.myblog_mithu.ternaryOperator;

public class TernaryOperator {
    public static void main(String[] args) {


//    variable = (condition) ? expressionIfTrue : expressionIfFalse;

        int x = 10;
        int y = 20;

        // Using the ternary operator to find the maximum of x and y
//        int max = (x > y) ? x : y;
        int max = Math.max(x, y);

        System.out.println("The maximum value is: " + max);
    }

}
