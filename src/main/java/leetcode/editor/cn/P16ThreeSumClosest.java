//给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和
//。假定每组输入只存在唯一答案。 
//
// 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
//
//与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
// 
// Related Topics 数组 双指针


package leetcode.editor.cn;

import java.util.Arrays;

//Java：最接近的三数之和
public class P16ThreeSumClosest {
    public static void main(String[] args) {
        Solution solution = new P16ThreeSumClosest().new Solution();
        // TO TEST
        int[] nums = new int[]{-1, 2, 1, -4};
        System.out.println(solution.threeSumClosest(nums, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int sum = 0;
            int offset = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int target2 = target - nums[i];
                int m = i + 1;
                int n = nums.length - 1;

                while (m < n) {
                    int sum2 = nums[m] + nums[n];
                    if (sum2 > target2) {
                        m++;
                    } else if (sum2 < target2) {
                        n--;
                    } else {
                        return target;
                    }
                    int offset2 = Math.abs(sum2 - target2);
                    if (offset > offset2) {
                        sum = sum2 + nums[i];
                        offset = offset2;
                    }
                }
            }
            return sum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}