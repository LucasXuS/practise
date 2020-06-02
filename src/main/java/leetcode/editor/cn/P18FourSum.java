//给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c +
// d 的值与 target 相等？找出所有满足条件且不重复的四元组。 
//
// 注意： 
//
// 答案中不可以包含重复的四元组。 
//
// 示例： 
//
// 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
//
//满足要求的四元组集合为：
//[
//  [-1,  0, 0, 1],
//  [-2, -1, 1, 2],
//  [-2,  0, 0, 2]
//]
// 
// Related Topics 数组 哈希表 双指针


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：四数之和
public class P18FourSum {
    public static void main(String[] args) {
        Solution solution = new P18FourSum().new Solution();
        // TO TEST
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        List<List<Integer>> lists = solution.fourSum(nums, 0);
        for (List<Integer> list : lists){
            for (Integer i : list){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums);
            List<List<Integer>> lists = new ArrayList<>();
            for (int a = 0; a < nums.length - 4; a++) {
                for (int b = a + 1; b < nums.length - 3; b++) {
                    int c = b + 1;
                    int d = nums.length - 1;
                    while (c < d) {
                        if (nums[a] + nums[b] + nums[c] + nums[d] > target) {
                            d--;
                        } else if (nums[a] + nums[b] + nums[c] + nums[d] < target) {
                            c++;
                        } else {
                            List<Integer> list = new ArrayList<>();
                            list.add(nums[a]);
                            list.add(nums[b]);
                            list.add(nums[c]);
                            list.add(nums[d]);
                            lists.add(list);
                            while (c < d && nums[c + 1] == nums[c]) {
                                c++;
                            }
                            while (c < d && nums[d - 1] == nums[d]) {
                                d--;
                            }
                            c++;
                            d--;
                        }
                    }
                }
            }
            return lists;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}