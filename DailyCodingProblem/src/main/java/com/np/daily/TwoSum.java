package com.np.daily;

import java.util.HashSet;
import java.util.Set;

public class TwoSum {

    private static boolean hasTwoSum(int[] arr, int sum) {
        Set<Integer> diffMap = new HashSet<>();
        for (int j : arr) {
            int diff = sum - j;
            if (diffMap.contains(j)) {
                return true;
            } else {
                diffMap.add(diff);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(hasTwoSum(new int[]{10, 15, 3, 7}, 11));
    }
}
