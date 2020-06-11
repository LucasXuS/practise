//合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。 
//
// 示例: 
//
// 输入:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//输出: 1->1->2->3->4->4->5->6 
// Related Topics 堆 链表 分治算法


package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

//Java：合并K个排序链表
public class P23MergeKSortedLists {
    public static void main(String[] args) {
        Solution solution = new P23MergeKSortedLists().new Solution();
        ListNode[] lists = new ListNode[]{solution.initList(new int[]{2}), solution.initList(new int[]{1}), solution.initList(new int[]{3})};
        // TO TEST
        System.out.println(solution.getIndex(new int[]{1,4,6,8,12,56}, 7));


    }

    //leetcode submit region begin(Prohibit modification and deletion)
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            Arrays.sort(lists, Comparator.comparingInt(o -> o.val));

            return null;
        }

        public int find(ListNode[] lists) {
            ListNode obj = lists[0];
            int i = 1;
            int j = lists.length - 1;
            while (i < j) {
                int mid = (j + i) / 2;
                int half = lists[mid].val;
                if (obj.val < half) {
                    i = mid + 1;
                } else if (obj.val > half) {
                    j = mid - 1;
                }
            }
            return 0;

        }

        public ListNode initList(int[] arr) {
            ListNode head = null;
            ListNode node = null;
            for (int i = 0; i < arr.length; i++) {
                if (i == 0) {
                    node = new ListNode(arr[0]);
                    head = node;
                } else {
                    ListNode temp = new ListNode(arr[i]);
                    node.next = temp;
                    node = temp;
                }
            }
            return head;
        }

        public void printList(ListNode head) {
            while (head != null) {
                System.out.println(head.val);
                head = head.next;
            }
        }

        public int getIndex(int[] arr, int val){
            int i = 0;
            int j = arr.length - 1;
            while (i < j) {
                int mid = (j + i) / 2;
                int half = arr[mid];
                if (arr[mid] < half) {
                    i = mid + 1;
                } else if (arr[mid] > half) {
                    j = mid - 1;
                }
            }
            return i;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}