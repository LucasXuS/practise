package leetcode.editor.cn;
//罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。 
//
// 字符          数值
//I             1
//V             5
//X             10
//L             50
//C             100
//D             500
//M             1000 
//
// 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V + I
//I 。 
//
// 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
// 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况： 
//
// 
// I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。 
// X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
// C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。 
// 
//
// 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。 
//
// 
//
// 示例 1: 
//
// 输入: "III"
//输出: 3 
//
// 示例 2: 
//
// 输入: "IV"
//输出: 4 
//
// 示例 3: 
//
// 输入: "IX"
//输出: 9 
//
// 示例 4: 
//
// 输入: "LVIII"
//输出: 58
//解释: L = 50, V= 5, III = 3.
// 
//
// 示例 5: 
//
// 输入: "MCMXCIV"
//输出: 1994
//解释: M = 1000, CM = 900, XC = 90, IV = 4. 
//
// 
//
// 提示： 
//
// 
// 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。 
// IC 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。 
// 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。 
// 
// Related Topics 数学 字符串 
// 👍 1187 👎 0


import java.util.Arrays;
import java.util.List;

//Java：罗马数字转整数
public class P13RomanToInteger {
    public static void main(String[] args) {
        Solution solution = new P13RomanToInteger().new Solution();
        System.out.println(solution.romanToInt("CMXCIX"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int romanToInt(String s) {
            String[] spec = new String[]{"CM","CD","XC","XL","IX","IV"};
            List<String> specList = Arrays.asList(spec);
            int num = 0;
            while (s.length() > 0){
                if(s.length() > 1){
                    String t2 = s.substring(0, 2);
                    if(specList.contains(t2)){
                        if(t2.equals("CM")){
                            num += 900;
                        }else if(t2.equals("CD")){
                            num += 400;
                        }else if(t2.equals("XC")){
                            num += 90;
                        }else if(t2.equals("XL")){
                            num += 40;
                        }else if(t2.equals("IX")){
                            num += 9;
                        }else if(t2.equals("IV")){
                            num += 4;
                        }
                        s = s.substring(2);
                        continue;
                    }
                }
                String t1 = s.substring(0, 1);
                if(t1.equals("M")){
                    num += 1000;
                }else if(t1.equals("D")){
                    num += 500;
                }else if(t1.equals("C")){
                    num += 100;
                }else if(t1.equals("L")){
                    num += 50;
                }else if(t1.equals("X")){
                    num += 10;
                }else if(t1.equals("V")){
                    num += 5;
                }else {
                    num += 1;
                }
                s = s.substring(1);
            }
            return num;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}