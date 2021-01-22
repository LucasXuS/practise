package leetcode.editor.cn;
//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划


//Java：最长回文子串
public class P5LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new P5LongestPalindromicSubstring().new Solution();
        System.out.println(solution.longestPalindrome("cbbd"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestPalindrome(String s) {
            boolean[][] p = new boolean[s.length()][s.length()];
            int maxlength = 0;
            String ans = null;
            for (int len = 0; len < s.length(); len++) {
                for (int i = 0; i + len < s.length(); i++) {
                    int j = i + len;
                    if (len == 0) {
                        p[i][j] = true;
                    } else if (len == 1) {
                        p[i][j] = s.charAt(j) == s.charAt(i);
                    } else {
                        p[i][j] = p[i + 1][j - 1] && s.charAt(j) == s.charAt(i);
                    }
                    if(p[i][j] && len + 1 > maxlength){
                        ans = s.substring(i, j + 1);
                        maxlength = len + 1;
                    }
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}