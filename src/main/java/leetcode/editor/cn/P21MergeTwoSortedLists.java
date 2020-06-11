//将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 示例： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
// 
// Related Topics 链表


package leetcode.editor.cn;

//Java：合并两个有序链表
public class P21MergeTwoSortedLists {
    public static void main(String[] args) {
        Solution solution = new P21MergeTwoSortedLists().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode head = null;
            ListNode node = null;

            while (l1 != null && l2 != null) {
                if (node == null) {
                    if (l1.val < l2.val) {
                        head = new ListNode(l1.val);
                        l1 = l1.next;
                    } else {
                        head = new ListNode(l2.val);
                        l2 = l2.next;
                    }

                    node = head;
                } else {
                    ListNode listNode = null;
                    if (l1.val < l2.val) {
                        listNode = new ListNode(l1.val);
                        l1 = l1.next;
                    } else {
                        listNode = new ListNode(l2.val);
                        l2 = l2.next;
                    }
                    node.next = listNode;
                    node = node.next;
                }
            }
            if (l1 != null) {
                node.next = l1;
            }
            if (l2 != null) {
                node.next = l2;
            }
            return head;
        }

        public void printList(ListNode head) {
            while (head != null) {
                System.out.println(head.val);
                head = head.next;
            }
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
    }
    //leetcode submit region end(Prohibit modification and deletion)

}