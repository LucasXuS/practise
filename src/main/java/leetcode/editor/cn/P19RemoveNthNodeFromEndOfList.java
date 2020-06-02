//给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。 
//
// 示例： 
//
// 给定一个链表: 1->2->3->4->5, 和 n = 2.
//
//当删除了倒数第二个节点后，链表变为 1->2->3->5.
// 
//
// 说明： 
//
// 给定的 n 保证是有效的。 
//
// 进阶： 
//
// 你能尝试使用一趟扫描实现吗？ 
// Related Topics 链表 双指针


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：删除链表的倒数第N个节点
public class P19RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = new P19RemoveNthNodeFromEndOfList().new Solution();
        // TO TEST
        solution.printList(solution.removeNthFromEnd(solution.initList(new int[]{1}), 1));
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
        public ListNode removeNthFromEnd(ListNode head, int n) {
            List<ListNode> list = new ArrayList<>();
            ListNode node = head;
            while (node != null) {
                list.add(node);
                node = node.next;
            }
            if(list.size() == 1){
                return null;
            }
            if (n == 1) {
                ListNode temp = list.get(list.size() - 2);
                temp.next = null;
            } else if (n == list.size()) {
                return head.next;
            } else {
                ListNode temp1 = list.get(list.size() - n - 1);
                temp1.next = list.get(list.size() - n + 1);
            }
            return head;
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
    }
    //leetcode submit region end(Prohibit modification and deletion)

}