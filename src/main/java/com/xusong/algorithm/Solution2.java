package com.xusong.algorithm;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-04-26
 * @description: leetcode 题目6——题目10
 */
public class Solution2 {


    public static void main(String[] args) {
        String s = "mississippi";
        String p = "mis*is*ip*.";
        System.out.println(isMatch2(s, p));
    }

    // 题目六 这是个数学问题，只要找出每行的通项公式即可
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int T = numRows * 2 - 2;
        for (int i = 0; i < numRows; i++) {
            if (i > s.length()) {
                return stringBuilder.toString();
            }
            int k = 0;
            int index = i;
            int backT = 2 * i;
            int preT = T - backT;
            while (index < s.length()) {
                stringBuilder.append(s.charAt(index));
                k++;
                if ((k % 2 == 1 && preT == 0) || (k % 2 == 0 && backT == 0)) {
                    k++;
                }
                index += k % 2 == 0 ? backT : preT;
            }
        }
        return stringBuilder.toString();
    }

    // 题目七 这是个基本代码技巧问题
    public static int reverse(int x) {
        int coe = x < 0 ? -1 : 1;
        x = Math.abs(x);
        int res = 0;
        while (x > 0) {
            int temp = x % 10;
            res = 10 * res + temp;
            x = x / 10;
        }
        return res * coe;
    }

    // 题目八 这是个代码基础知识问题
    public static int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;
        int i = 0;
        // 首先要先清掉前面的空格
        while (i < str.length() && str.charAt(i) == ' ') {
            i++;
        }
        if (i >= str.length()) {
            return 0;
        }
        // 第一个字符是非空字符
        str = str.substring(i);
        int coe = 1;

        if (str.charAt(0) == '+' && str.length() > 1) {
            coe = 1;
            str = str.substring(1);
        } else if (str.charAt(0) == '-' && str.length() > 1) {
            coe = -1;
            str = str.substring(1);
        }
        i = 0;
        Long res = 0L;
        if (str.charAt(0) <= '9' && str.charAt(0) >= '0') {
            while (i < str.length() && str.charAt(i) <= '9' && str.charAt(i) >= '0') {
                int k = str.charAt(i) - '0';
                res = 10 * res + k;
                if ((res <= Integer.MIN_VALUE || (res > Integer.MAX_VALUE)) && coe < 0) {
                    return Integer.MIN_VALUE;
                }
                if ((res > Integer.MAX_VALUE || (res <= Integer.MIN_VALUE) && coe > 0)) {
                    return Integer.MAX_VALUE;
                }
                i++;
            }
            res = res * coe;
            return res.intValue();
        } else {
            return 0;
        }
    }

    // 题目九
    public static boolean isPalindrome(int x) {
        int ori = x;
        if (x < 0 || (x > 0 && x % 10 == 0)) {
            return false;
        }
        int pali = 0;
        while (x > 0) {
            pali = pali * 10 + x % 10;
            x = x / 10;
        }
        return pali == ori;
    }

    // 题目十


    //  1  自底向上无递归 + 动态规划
    // String s = "mississippi";
    // String p = "mis*is*ip*.";
    //  ppi
    //  p*.
    public static boolean isMatch1(String s, String p) {
        boolean[][] memo = new boolean[s.length() + 1][p.length() + 1];
        memo[s.length()][p.length()] = true;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                // 针对 i 和 j位置进行单独的比较,同时处理.的情况
                boolean firstMatch = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                // 这里是最复杂的情况，要考虑*
                if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
                    // && 比 || 优先
                    // * 代表0个或多个  所以 memo[i][j + 2]代表 0 个的情形  firstMatch && memo[i + 1][j] 代表多个的情形
                    memo[i][j] = memo[i][j + 2] || firstMatch && memo[i + 1][j];
                } else {
                    // 这是最基本的情况，完全不考虑 * 符号的情况，在不考虑* 的情况下 {i} {j} 符合,同时 [i + 1:] [j + 1:]符合，就是[i:] [j:]符合
                    memo[i][j] = firstMatch & memo[i + 1][j + 1];
                }
                System.out.println("s = " + s.substring(i) + ",p = " + p.substring(j) + ",memo[" + i + "][" + j + "] = " + memo[i][j]);

            }
        }
        return memo[0][0];
    }

    // 2 根据上面的方法改成递归 实际上 memo[i][j]是一种可以通过递归实现的方式 即 isMatch(s.substring(i), p.substring(j))
    public static boolean isMatch2(String s, String p) {
        // 递归出口
        if (p.equals(""))
            return s.equals("");
        boolean firstMatch = s.charAt(0) == p.charAt(0) || p.charAt(0) == '.';
        // 这里是最复杂的情况，要考虑*
        if (p.length() > 2 && p.charAt(1) == '*') {
            // && 比 || 优先
            // * 代表0个或多个  所以 memo[i][j + 2]代表 0 个的情形  firstMatch && memo[i + 1][j] 代表多个的情形
            return isMatch2(s, p.substring(2)) || firstMatch && isMatch2(s.substring(1), p);
        } else {
            // 这是最基本的情况，完全不考虑 * 符号的情况，在不考虑* 的情况下 {i} {j} 符合,同时 [i + 1:] [j + 1:]符合，就是[i:] [j:]符合
            return firstMatch & isMatch2(s.substring(1), p.substring(1));
        }
    }


}
