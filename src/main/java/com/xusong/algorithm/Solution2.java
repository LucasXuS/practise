package chapterX;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class Solution2 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
    }

    // 题目一
    // 1 暴力法
    public static int[] twoSum1(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int other = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (other == nums[j]) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return null;
    }

    // 2 使用hashmap 这种数据类型，是以内存为代价换取速度，此例子使用两次轮询
    public static int[] twoSum2(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int other = target - nums[i];
            if (map.get(other) != null) {
                res[0] = i;
                res[1] = map.get(other);
                return res;
            }
        }
        return null;
    }

    // 3 使用hashmap 只使用一次轮询
    public static int[] twoSum3(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int other = target - nums[i];
            // 如果没有other元素，代表还没到结束的时候，收集当前元素
            if (map.get(other) == null) {
                map.put(nums[i], i);
            } else {
                // 如果有other 本元素没必要存储，因为已经有结果了，第一个元素是other,第二个是本体
                res[0] = map.get(other);
                res[1] = i;
                return res;
            }
        }
        return null;
    }

    // 题目二
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 这个 0 位是废的，最后返回的时候不传
        ListNode p = l1, q = l2, dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = p != null ? p.val : 0;
            int y = q != null ? q.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            p = p == null ? null : p.next;
            q = q == null ? null : q.next;
        }
        if (carry > 0) {
            // 这里不需要 cur = cur.next 了 因为我们的链表已经创建完成。
            cur.next = new ListNode(carry);
        }
        // next 是为了跳过最初的0
        return dummyHead.next;
    }

    // 题目三
    // 1 暴力法 会产生超时
    public static int lengthOfLongestSubstring1(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            // 因为isUniqueCharString 不包含end所以，一直到s.length()
            for (int j = 0; j <= s.length(); j++) {
                if (isUniqueCharString1(s, i, j)) {
                    // j - i 就好，因为j不算
                    ans = Math.max(ans, j - i);
                }
            }
        }
        return ans;
    }

    public static boolean isUniqueCharString1(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        // end最大到s.length 所以不包含end
        for (int i = start; i < end; i++) {
            if (set.contains(s.charAt(i))) {
                return false;
            }
            set.add(s.charAt(i));
        }
        return true;
    }

    // 滑动窗口 创建set 保证set中的元素是连续且唯一的。
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int begin = 0, end = 0;
        int ans = 0;
        while (begin < n && end < n) {
            char c = s.charAt(end);
            // 如果set不包含，那么直接添加元素即可
            if (!set.contains(c)) {
                set.add(c);
                end++;
                ans = Math.max(ans, end - begin);// end最大可以到n，所以end - begin没问题
            } else {
                // 从begin开始删除set中的元素，一直删除到重复的元素位置 如abcdeefg 会一直删除到e，然后再下一轮添加下一个e，使得e是唯一。
                set.remove(s.charAt(begin));
                begin++;
            }
        }
        return ans;
    }

    // 滑动窗口，利用hashmap 替换hashset 来缩短begin滑动的环节
    public static int lengthOfLongestSubstring3(String s) {
        int ans = 0;
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int begin = 0, end = 0; end < s.length(); end++) {
            if (hashMap.containsKey(s.charAt(end))) {
                // 此时不需要像上次那样一个个删除了，而是直接跳到重复的位置
                begin = Math.max(begin, hashMap.get(s.charAt(end)));
            }
            // 前面的调整不代表后面不可以继续
            // 如果重复的话，确保hashmap里面保存的char的位置，是最大位置序号
            hashMap.put(s.charAt(end), end);
            ans = Math.max(ans, end - begin);// begin 小一个，end正常 所以end - begin没问题。
        }
        return ans;
    }

    // 当输入全是字母的时候，根据ascii码，所有的字符一共有128中，我们用int[128] 去代替 hashmap
    // int[128] 索引 是 character对应的ascii码， 数值是最大位置
    public static int lengthOfLongestSubstring4(String s) {
        int ans = 0;
        int[] arr = new int[128];
        for (int i = 0, j = 0; j < s.length(); j++) {
            // 如果本不存在，arr[s.charAt(j)]就是0，i不变，如果不是，代表之前存在，直接跳到最近的地带（仍然少1）
            i = Math.max(arr[s.charAt(j)], i);
            // 确保hashmap里面保存的char的位置，是最大位置序号
            arr[s.charAt(j)] = j;
            ans = Math.max(ans, j - i);
        }
        return ans;
    }


    // 题目四
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        //          left_A             |        right_A
        //    A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
        //          left_B             |        right_B
        //    B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
        //          left_part          |        right_part
        //    A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
        //    B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]

        // 首先确认 m < n 因为确认这件事情在二分法和边界条件判断时有用
        if (nums1.length > nums2.length) {
            int[] tempArray = nums2;
            nums2 = nums1;
            nums1 = tempArray;
        }
        int m = nums1.length;
        int n = nums2.length;
        // 在这里针对num1走二分法
        int begin = 0, end = m;
        while (begin < end) {
            int i = (begin + end) / 2;
            // 这个是通过计算设计的，因为m+n为偶数时，(m+n)/2 与 （m+n+1）/2相等，
            // 这时m+n为奇偶都是一个算式
            int j = (m + n + 1) / 2 - i;
            // 此时，我们认为i过大 另外i>0时，通过数学推导，j<n
            if (i > 0 && nums1[i - 1] > nums2[j]) {
                begin = i + 1;
            }
            // 此时，我们认为i过小 另外i<m时，通过数学推导，j>0
            else if (i < m && nums1[i] < nums2[j - 1]) {
                end = i - 1;
            }
            // 剩下的情况都是我们期望的情况
            else {
                int maxLeft = 0;
                if (i == 0) {
                    // 代表num1全面大于num2
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    // 代表num2全面大于num1
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                // 我们通过 j = (m + n + 1) / 2 - i 可以知道m+n在奇数情况下，j偏大，i+j偏大，
                // 所以多的元素在左边
                if ((i + j) % 2 == 1) {
                    return maxLeft;
                }
                int minRight = 0;
                if (i == m) {
                    // 代表num1全面小于num2
                    minRight = nums2[j];
                } else if (j == n) {
                    // 代表num2全面小于num1
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums1[i], nums2[j]);
                }
                return (maxLeft + minRight) / 2.0;
            }

        }
        return 0.0;
    }

    // 题目五
    // 方法1 最长公共子串
    public static String longestPalindrome1(String s) {

    }


}