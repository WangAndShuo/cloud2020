package com.atguigu.springcloud.controller;

public class Main {

    public static int minSubArrayLen(int s, int[] nums) {
        int min = s;
        int a = 0;
        int b = 0;
        int all = 0;
        if (nums.length == 0) {
            return 0;
        }
        while (b < nums.length) {
            if (all > s) {
                min = min > (b + 1 - a) ? (b + 1 - a) : min;
                a++;
                all -= nums[a];
            } else {
                all += nums[b];
                b++;
            }

        }
        return min;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int l = 11;
        System.out.println(minSubArrayLen(l, arr));
    }
}
