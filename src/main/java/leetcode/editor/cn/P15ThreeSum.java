//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例： 
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics 数组 双指针


package leetcode.editor.cn;

import java.util.*;

//Java：三数之和
public class P15ThreeSum {
    public static void main(String[] args) {
        Solution solution = new P15ThreeSum().new Solution();
        // TO TEST
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = solution.threeSum2(nums);
        StringBuilder stringBuilder = new StringBuilder();
        for(List<Integer> list : lists){
            for (Integer a : list){
                stringBuilder.append(a).append(",");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> lists = new ArrayList<>();
            for(int i = 0; i < nums.length; i++){
                int target = 0 - nums[i];
                Map<Integer, Integer> map = new HashMap<>();
                for (int j = i + 1; j < nums.length; j++) {
                    List<Integer> list = new ArrayList<>();
                    int other = target - nums[j];
                    if (map.get(other) == null) {
                        map.put(nums[j], j);
                    } else {
                        boolean exist = false;
                        for(List<Integer> temp : lists){
                            if(temp.get(0) == nums[i] && temp.get(1) == other){
                                exist = true;
                            }
                        }
                        if(!exist){
                            list.add(nums[i]);
                            list.add(other);
                            list.add(nums[j]);
                        }
                    }
                    if(list.size() > 0){
                        lists.add(list);
                    }
                }
            }
            return lists;
        }
        public List<List<Integer>> threeSum2(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> lists = new ArrayList<>();
            for(int i = 0; i < nums.length; i++){
                if(i > 0 && nums[i] == nums[i - 1]){
                    continue;
                }
                int target = 0 - nums[i];
                int m = i + 1;
                int n = nums.length - 1;

                while (m < n){
                    int sum = nums[m] + nums[n];
                    if(sum < target){
                        m++;
                    }else if(sum > target){
                        n--;
                    }else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[m]);
                        list.add(nums[n]);
                        lists.add(list);
                        m++;
                    }
                }
            }
            return lists;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}