//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例: 
//
// 给定 1->2->3->4, 你应该返回 2->1->4->3.
// 
// Related Topics 链表


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：两两交换链表中的节点
public class P24SwapNodesInPairs {
    public static void main(String[] args) {
        Solution solution = new P24SwapNodesInPairs().new Solution();
        // TO TEST
        ListNode head = solution.initList(new int[]{1,2,3,4});
        solution.printList(head);
        head = solution.swapPairs(head);
        solution.printList(head);
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
        public ListNode swapPairs(ListNode head) {
            List<ListNode> listNodes = new ArrayList<>();
            ListNode node = head;
            while (node != null) {
                listNodes.add(node);
                node = node.next;
            }
            for (int i = 0; i < listNodes.size(); i = i + 2) {
                if (i == listNodes.size() - 1)
                    break;
                ListNode node1 = listNodes.get(i);
                ListNode node2 = listNodes.get(i + 1);
                ListNode left = i == 0 ? null : listNodes.get(i - 2);
                ListNode right = i + 1 == listNodes.size() - 1 ? null : listNodes.get(i + 2);
                node2.next = node1;
                node1.next = right;
                if(left != null){
                    left.next = node2;
                }
            }
            if(listNodes.size() >= 2){
                for (ListNode listNode : listNodes){
                    if(listNode.next == head){
                        head = listNode;
                    }
                }
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