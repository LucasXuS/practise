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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//Java：合并K个排序链表
public class P23MergeKSortedLists {
    public static void main(String[] args) {
        Solution solution = new P23MergeKSortedLists().new Solution();
        ListNode[] lists = new ListNode[]{solution.initList(new int[]{1, 4, 5}), solution.initList(new int[]{1, 3, 4}), solution.initList(new int[]{2, 6})};
        // TO TEST
        //System.out.println(solution.getIndex(new int[]{1, 4, 6, 8, 12, 56}, 7));
        solution.printList(solution.mergeKLists(lists));
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
            ListNode result = null;
            ListNode head = null;
            lists = washData(lists);
            Arrays.sort(lists, Comparator.comparingInt(o -> o.val));
            while (lists.length > 0) {
                ListNode node = lists[0];
                if (result == null) {
                    result = new ListNode(node.val);
                    head = result;
                } else {
                    result.next = new ListNode(node.val);
                    result = result.next;
                }
                node = node.next;
                if (node == null) {
                    ListNode[] newLists = new ListNode[lists.length - 1];
                    if (lists.length > 1) {
                        System.arraycopy(lists, 1, newLists, 0, newLists.length);
                    }
                    lists = newLists;
                } else {
                    switchElements(lists, node);
                }
            }
            return head;
        }

        public void switchElements(ListNode[] lists, ListNode node) {
            int i = 1;
            while (i < lists.length && node.val > lists[i].val) {
                lists[i - 1] = lists[i];
                i++;
            }
            lists[i - 1] = node;
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

        public int getIndex(int[] arr, int val) {
            int i = 0;
            int j = arr.length - 1;
            while (i < j) {
                int mid = (j + i) / 2;
                int half = arr[mid];
                if (half < val) {
                    i = mid + 1;
                } else {
                    j = mid - 1;
                }
            }
            return i;
        }


        public ListNode[] washData(ListNode[] lists){
            ArrayList<ListNode> listNodeList = new ArrayList<>();
            for(ListNode node : lists){
                if(node != null){
                    listNodeList.add(node);
                }
            }
            lists = new ListNode[listNodeList.size()];
            for(int i = 0; i < listNodeList.size(); i++){
                lists[i] = listNodeList.get(i);
            }
            return lists;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}