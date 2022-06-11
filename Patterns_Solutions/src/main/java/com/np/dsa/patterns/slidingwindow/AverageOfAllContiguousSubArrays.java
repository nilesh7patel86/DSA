package com.np.dsa.patterns.slidingwindow;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class AverageOfAllContiguousSubArrays {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(getAverage(new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2}, 5)));
//        System.out.println(Arrays.toString(getAverage(5, new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2})));
//        System.out.println(Arrays.toString(getAverage2(5, new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2})));
//
//        System.out.println(Arrays.toString(getAverage(new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2}, 3)));
//        System.out.println(Arrays.toString(getAverage(3, new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2})));
//        System.out.println(Arrays.toString(getAverage2(3, new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2})));
//
//        System.out.println(Arrays.toString(getAverage(new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2}, 2)));
//        System.out.println(Arrays.toString(getAverage(2, new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2})));
//        System.out.println(Arrays.toString(getAverage2(2, new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2})));
//
//        System.out.println(Arrays.toString(getAverage(new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2}, 6)));
//        System.out.println(Arrays.toString(getAverage(6, new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2})));
//        System.out.println(Arrays.toString(getAverage2(6, new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2})));
//
//        System.out.println(Arrays.toString(getAverage(new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2, 6}, 6)));
//        System.out.println(Arrays.toString(getAverage(6, new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2, 6})));
//        System.out.println(Arrays.toString(getAverage2(6, new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2, 6})));

        //System.out.println(Arrays.toString(getAverage(IntStream.rangeClosed(1,10000).toArray(),5)));
        //System.out.println(Arrays.toString(getAverage(10,IntStream.rangeClosed(1,100000).toArray())));
        //System.out.println(Arrays.toString(getAverage2(10,IntStream.rangeClosed(1,100000).toArray())));
        AtomicLong mySum = new AtomicLong();
        AtomicLong sampleSum = new AtomicLong();
        IntStream.range(0, 1000).forEach(value -> {
            // System.out.print("my method: "+getAverageTime(10,IntStream.rangeClosed(1,100000).toArray()));
            // System.out.println(" sample method: "+getAverage2Time(10,IntStream.rangeClosed(1,100000).toArray()));
            sampleSum.addAndGet(getAverage2Time(10, IntStream.rangeClosed(1, 100000).toArray()));
            mySum.addAndGet(getAverageTime(10, IntStream.rangeClosed(1, 100000).toArray()));
        });
        System.out.println("my avg="+mySum.get()/1000);
        System.out.println("sample avg="+sampleSum.get()/1000);
    }

    /*
    BRUTE FORCE
    O( K * (N-K+1)) = O(KN-K^2+K) ~ O(KN) given K<N
     */
    private static float[] getAverage(int[] arr, int K) {
        long l = System.nanoTime();
        int i1 = arr.length - K + 1;
        float[] average = new float[i1];
        for (int i = 0; i < i1; i++) {
            int sum = 0;
            for (int j = 0; j < K; j++) {
                sum += arr[i + j];
            }
            average[i] = ((float) sum) / K;
        }
        System.out.println("Time = " + (System.nanoTime() - l));
        return average;
    }

    /*Sliding window*/
    private static float[] getAverage(int K, int[] arr) {
        long l = System.nanoTime();
        int i1 = arr.length - K + 1;
        float[] average = new float[i1];
        int start = 0;
        int end = K - 1;
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += arr[i];
        }
        average[start] = ((float) sum) / K;
        start++;
        end++;
        while (end <= arr.length - 1) {
            sum = sum - arr[start - 1] + arr[end];
            average[start] = ((float) sum) / K;
            start++;
            end++;

        }
        System.out.println("Time = " + (System.nanoTime() - l));
        return average;
    }

    private static float[] getAverage2(int K, int[] arr) {
        long l = System.nanoTime();
        float[] average = new float[arr.length - K + 1];
        float windowSum = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];
            if (windowEnd >= (K - 1)) {
                average[windowStart] = windowSum / K;
                windowSum -= arr[windowStart];
                windowStart++;
            }
        }
        System.out.println("Time = " + (System.nanoTime() - l));
        return average;
    }


    private static long getAverageTime(int K, int[] arr) {
        long l = System.nanoTime();
        int i1 = arr.length - K + 1;
        float[] average = new float[i1];
        int start = 0;
        int end = K - 1;
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += arr[i];
        }
        average[start] = ((float) sum) / K;
        start++;
        end++;
        while (end <= arr.length - 1) {
            sum = sum - arr[start - 1] + arr[end];
            average[start] = ((float) sum) / K;
            start++;
            end++;

        }
        //System.out.println("Time = "+(System.nanoTime()-l));
        return System.nanoTime() - l;
    }

    private static long getAverage2Time(int K, int[] arr) {
        long l = System.nanoTime();
        float[] average = new float[arr.length - K + 1];
        float windowSum = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];
            if (windowEnd >= (K - 1)) {
                average[windowStart] = windowSum / K;
                windowSum -= arr[windowStart];
                windowStart++;
            }
        }
        //System.out.println("Time = "+(System.nanoTime()-l));
        return System.nanoTime() - l;
    }
}
