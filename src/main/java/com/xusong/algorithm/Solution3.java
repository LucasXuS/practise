package com.xusong.algorithm;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-05-08
 * @description: leetcode 题目11——题目15
 */
public class Solution3 {
    public static void main(String[] args) {
        System.out.println(intToRoman2(1994));
    }


    // 题目十一
    // 双指针法  实际上是个简单不等式证明得到的方法
    public static int maxArea(int[] height) {
        int volumn = 0;
        int left = 0;
        int right = height.length - 1;
        while (right > left) {
            int volumnTemp = Math.min(height[left], height[right]) * (right - left);
            volumn = Math.max(volumn, volumnTemp);
            if (height[left] >= height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return volumn;
    }

    // 题目十二
    // 方法1 按照位来做
    public static String intToRoman1(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        int st = 10000;
        char[] five = new char[]{'r', 'D', 'L', 'V'};
        char[] one = new char[]{'M', 'C', 'X', 'I'};
        for (int i = 0; i < 4; i++) {
            st = st / 10;
            int flag = num / st;
            if (flag > 0) {
                num = num - flag * st;
                if (flag == 4) {
                    stringBuilder.append(one[i]).append(five[i]);
                } else if (flag == 9) {
                    stringBuilder.append(one[i]).append(one[i - 1]);
                } else if (flag < 4) {
                    while (flag > 0) {
                        stringBuilder.append(one[i]);
                        flag--;
                    }
                } else {
                    stringBuilder.append(five[i]);
                    flag = flag - 5;
                    while (flag > 0) {
                        stringBuilder.append(one[i]);
                        flag--;
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    public static String intToRoman2(int num) {
        int values[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String reps[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                res.append(reps[i]);
            }
        }
        return res.toString();
    }
}
