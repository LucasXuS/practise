package com.xusong.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, 2020-04-20
 * @description: 用于查找公共字符串的后缀树 首次编写用于解决最大回文字符串问题
 */
public class LCS {

    // 节点分为三部分
    private class SuffixNode {
        // 节点中所保存的字符串
        private String val;
        // 子节点集合
        private List<SuffixNode> children = new ArrayList<>();
        // 是否为后缀字符串末尾，注意，后缀字符串末尾节点未必是树的子叶节点，但是子叶结点一定是某后缀字符串的末尾
        // 举例说明 abcabdab  ab肯定不是子叶节点，但是肯定会是后缀字符串ab的末尾
        private boolean terminal;

        public SuffixNode() {
            val = "";
        }

        public SuffixNode(String text) {
            val = text;
        }
    }

    // 字符串
    private String text;
    // 根节点，对后缀树而言，根节点没有数据
    private SuffixNode root;
    // 中止符集合，用于分割和区分多条字符串
    private char[] terminals;

    public LCS(String text, char[] terminals) {
        this.text = text;
        this.terminals = terminals;
    }


    public void buildSuffixTree() throws Exception {
        if (root == null) {
            root = new SuffixNode();
        }
        for (int i = 0; i < text.length(); i++) {
            // 以bananas为例，插入顺序是 bananas ananas nanas anas nas as s
            insert(root, text.substring(i));
        }
    }


    private void insert(SuffixNode node, String text) throws Exception {

        // 我们希望 node 的所有子节点的字符串值的首字母按照字母表的顺序排列，如果text的首字母排在所有节点之后，那么就需要在末尾添加
        boolean needToAddLast = true;

        for (int i = 0; i < node.children.size(); i++) {
            SuffixNode child = node.children.get(i);
            // 处理后缀树，是以处理字符串的后缀为前提的，因此我们需要研究的都是前面j个字符相同后的情况
            // 因此首先我们需要获取相同的部分j
            int n = Math.min(child.val.length(), text.length());
            int j = 0;
            for (; j < n; j++) {
                if (child.val.charAt(j) != text.charAt(j)) {
                    break;
                }
            }
            if (j == 0) {
                // j==0时，代表text和child.val完全不同
                if (child.val.charAt(0) > text.charAt(0)) {
                    // 此处代表text优先于child 那么就创建一个新的节点，插入在i这个位置上
                    SuffixNode newChild = new SuffixNode(text);
                    newChild.terminal = true;
                    node.children.add(i, newChild);
                    needToAddLast = false;
                } else {
                    // 此处只可能存在child.val.charAt(0)<text.charAt(0)的情况，而不是相等，因为相等时j != 0
                    // 此时我们应该让text和后面的child比较，看看需不需要合并或者在末尾添加
                    continue;
                }
            } else if (j == n) {
                // j == n 有三种情况  1 child.val与text 完全一致 2 child.val 包含 text 3 text 包含 child.val

                if (child.val.length() == text.length()) {
                    // 这是完全一致的情况
                    if (child.terminal) {
                        throw new Exception("存在两条完全相同的后缀，是不合理的！");
                    } else {
                        // 否则我们就定义这个为后缀字符串结尾
                        child.terminal = true;
                    }
                } else if (child.val.length() > text.length()) {
                    // 这是child.val 包含 text的情况
                    // 具体过程如图所示, 总的来说就是child节点要完成一个自我分裂的过程

                    // 首先获取分裂的另一个元素(对于子节点而言，将完全替代child)：
                    String subChildString = child.val.substring(text.length(), child.val.length());
                    SuffixNode subChild = new SuffixNode(subChildString);
                    subChild.terminal = child.terminal;
                    subChild.children = child.children;

                    // child重新构建：
                    child.val = text;
                    child.terminal = true;
                    child.children.clear();
                    child.children.add(subChild);


                } else {
                    // 这是text 包含 child.val的情况
                }

            } else {

            }
        }
    }

    public String findLCS() {

    }
}
