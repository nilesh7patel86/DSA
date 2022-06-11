package com.np.daily;

import java.util.ArrayList;
import java.util.List;

public class NumberOfDecodeWays {
//    Map<String, String> charmap = new HashMap<String, String>() {{
//        put("1", "a");
//        put("2", "b");
//        put("3", "c");
//        put("4", "d");
//        put("5", "e");
//        put("6", "f");
//        put("7", "g");
//        put("8", "h");
//        put("9", "i");
//        put("10", "j");
//        put("11", "k");
//        put("12", "l");
//        put("13", "m");
//        put("14", "n");
//        put("15", "o");
//        put("16", "p");
//        put("17", "q");
//        put("18", "r");
//        put("19", "s");
//        put("20", "t");
//        put("21", "u");
//        put("22", "v");
//        put("23", "w");
//        put("24", "x");
//        put("25", "y");
//        put("26", "z");
//    }};

    static int decodeAllCombinations(String msg) {
        if (msg == null) throw new RuntimeException("null/empty message");
        char[] chars = msg.toCharArray();

        if (chars.length == 0 || (chars.length > 1 && chars[0] == 0)) {
            return 0;
        }

        List<String> allCombinations = new ArrayList<>();
        getAllCombinations(chars, chars.length, allCombinations);
        System.out.println(allCombinations);
        return countCombinations(chars, chars.length);
    }

    private static void getAllCombinations(char[] chars, int length, List<String> allCombinations) {
        if (length == 0 || length == 1) {
            allCombinations.add(new String(chars));
            return;
        }
        if (chars[0] == '0') {
            return;
        }

        if(chars[length-1]>'0')
            getAllCombinations(chars,length-1,allCombinations);
        if (chars[length - 2] == '1' || (chars[length - 2] == '2' && chars[length - 1] < '7')) {
            getAllCombinations(chars,length-2,allCombinations);
        }
    }

    private static int countCombinations(char[] chars, int length) {
//        System.out.print(Arrays.toString(chars)
//                .replace("[", "")
//                .replace("]", "")
//                .replace(",", "")
//                .replace(" ", "")
//                .substring(0, length));
//        System.out.print("\n");
        // base cases
        if (length == 0) {
//            System.out.print("\t");
//            System.out.print(chars[0]);
            return 1;
        }
        if (length == 1) {
//            System.out.print("\t");
//            System.out.print(chars[1]);
            return 1;
        }

        // if the first digit is zero it is not a number
        if (chars[0] == 0) {
            return 0;
        }

        int count = 0;

        // if last char is 0 then it won't resolve to an alphabet, unless it is the last digit of 10, 20.
//        System.out.print(Arrays.toString(chars)
//                .replace("[", "")
//                .replace("]", "")
//                .replace(",", "")
//                .replace(" ", "")
//                .substring(0, length));
//        System.out.print("\t");
        // so we count combinations when last digit is not zero
        if (chars[length - 1] > '0') {
            count = countCombinations(chars, length - 1);
        }
        // If the last two digits form a number
        // smaller than or equal to 26,
        // then consider last two digits and recur

        // if the first digit of the two digit sequence is 1 or 2,
        // then the last two digits can form a character from 10 to 26
        // so we also need to check the last digit is 6 or below
        if (chars[length - 2] == '1' || (chars[length - 2] == '2' && chars[length - 1] < '7')) {
            count += countCombinations(chars, length - 2);
        }
//        System.out.println("\n---- [" + count + "]");
        return count;
    }


    public static void main(String[] args) {
        System.out.println(decodeAllCombinations("111"));
    }
}
