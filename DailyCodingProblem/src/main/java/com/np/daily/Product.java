package com.np.daily;

import java.util.Arrays;

public class Product {
//    Given an array of integers, return a new array such that each element at index i
//    of the new array is the product of all the numbers in the original array except the one at i.
//
//    For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24].
//    If our input was [3, 2, 1], the expected output would be [2, 3, 6].


    public static int[] product(int[] inp, boolean useDiv) {
        int product = 1;
        boolean hasZero = false;
        for (int i = 0; i < inp.length; i++) {
            if (inp[i] != 0) {
                product *= inp[i];
            } else {
                hasZero = true;
            }
        }

       // System.out.println(product);
        int[] res = new int[inp.length];
        for (int i = 0; i < inp.length; i++) {
            if (inp[i] == 0) {
                res[i] = product;
            } else if (hasZero) {
                res[i] = 0;
            } else {
                if (useDiv)
                    res[i] = product / inp[i];
                else
                    res[i] = divide(product, inp[i]);
            }
        }
        return res;
    }

    private static int divide(int dividend, int divisor) {
        boolean isNegative = (dividend < 0) ^ (divisor < 0);
        int quotient = 0;
        while (dividend >= divisor) {
            dividend -= divisor;
            quotient++;
        }
        return isNegative ? -quotient : quotient;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(product(new int[]{1, 2, 3, 4, 5},true)));
        System.out.println(Arrays.toString(product(new int[]{1, 2, 3, 4, 5},false)));
        System.out.println(Arrays.toString(product(new int[]{1, 2, 0, 4, 5},true)));
        System.out.println(Arrays.toString(product(new int[]{1, 2, 0, 4, 5},false)));
        System.out.println(Arrays.toString(product(new int[]{1, 2, 0, 4, 0},true)));
        System.out.println(Arrays.toString(product(new int[]{1, 2, 0, 4, 0},false)));
        System.out.println(Arrays.toString(product(new int[]{3, 2, 1},true)));
        System.out.println(Arrays.toString(product(new int[]{3, 2, 1},false)));


    }
}
