package com.np.daily;

public class FirstMissingPositiveInteger {

    // Given an array of integers, find the first missing positive integer in linear time and constant space.
    // In other words, find the lowest positive integer that does not exist in the array.
    // The array can contain duplicates and negative numbers as well.

    // For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.

    // You can modify the input array in-place.
    private static int firstMissingPositiveInteger(int[] arr) {
        int low = arr[0] - 1, high = arr[0] + 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > 0) {
                if (arr[i] == low) {
                    low = arr[i] - 1;
                } else if (arr[i] == high) {
                    high = high + 1;
                }
            }
        }
        if (low > 0 && low < high)
            return low;
        else
            return high;
    }

    public static void main(String[] args) {
        System.out.println(firstMissingPositiveInteger(new int[]{3, 4, -1, 1}));
        System.out.println(firstMissingPositiveInteger(new int[]{4, 3, -1, -1}));
        System.out.println(firstMissingPositiveInteger(new int[]{3, 4, 5, -1, -1}));
        System.out.println(firstMissingPositiveInteger(new int[]{1, 2, 3, 0, 3, 2, 1}));
        System.out.println(firstMissingPositiveInteger(new int[]{1, 2, 3, 4, 5, 7, 9}));
        System.out.println(firstMissingPositiveInteger(new int[]{3, -1, 2, -2, 1, -3, 0}));
    }
}
