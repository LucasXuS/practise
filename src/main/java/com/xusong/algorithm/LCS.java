package com.xusong.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        private List<SuffixNode> children = new LinkedList<>();
        // 是否为后缀字符串末尾，注意，后缀字符串末尾节点未必是树的子叶节点，但是子叶结点一定是某后缀字符串的末尾
        // 举例说明 abcabdab  ab肯定不是子叶节点，但是肯定会是后缀字符串ab的末尾
        // 另，在注释中我们用#标识字符串末尾
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
        Map<Integer, SuffixNode> modMap = new HashMap<>();
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
                    //e.g. child="e" (currNode="abc")
                    //     abc                     abc
                    //    /  /    =========>      / | /
                    //   e    f   insert "c"     c# e  f
                    SuffixNode newChild = new SuffixNode(text);
                    newChild.terminal = true;
                    modMap.put(i, newChild);
                    needToAddLast = false;
                    break;
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
                        //e.g. child="ab"
                        //     ab                    ab#
                        //    /  /    =========>    /   /
                        //   e    f   insert "ab"  e     f
                        child.terminal = true;
                    }
                } else if (child.val.length() > text.length()) {
                    // 这是child.val 包含 text的情况
                    // 具体过程如图所示, 总的来说就是child节点要完成一个自我分裂的过程
                    //e.g. child="abc#"
                    //     abc#                      ab#
                    //    /   /      =========>      /
                    //   e     f     insert "ab"    c#
                    //                             /  /
                    //                            e    f
                    // 首先获取分裂的另一个元素(对于子节点而言，将完全替代child)：
                    String subChildString = child.val.substring(text.length(), child.val.length());
                    SuffixNode subChild = new SuffixNode(subChildString);
                    subChild.terminal = child.terminal;
                    subChild.children.addAll(child.children);

                    // child重新构建：
                    child.val = text;
                    child.terminal = true;
                    child.children.clear();
                    child.children.add(subChild);


                } else {
                    // 这是text 包含 child.val的情况
                    // 本质这是text被分裂的过程，公共的部分保持不变，多的部分以child为root节点进行插入
                    // 因此解决方案是个递归的过程，相当于将此child作为节点，插入公共字符串以外的部分，如图所示：
                    //e.g. child="ab#"
                    //     ab#                    ab#
                    //    /  /    ==========>    / | /
                    //   e    f   insert "abc"  c# e  f
                    String insertString = text.substring(child.val.length());
                    insert(child, insertString);
                }
                needToAddLast = false;
                break;
            } else {
                // 0 < j < n的情况
                // 这是一个text 和 child.val同时被分裂的过程
                //     abc#                     ab
                //    /  /     ==========>     / /
                //   e    f   insert "abd"    c#  d#
                //                           /  /
                //                          e    f
                String commonString = text.substring(0, j);

                String addNodeString = child.val.substring(j);
                SuffixNode nodeChild = new SuffixNode(addNodeString);
                nodeChild.terminal = child.terminal;
                nodeChild.children.addAll(child.children);

                String addTextString = text.substring(j);
                SuffixNode textChild = new SuffixNode(addTextString);
                textChild.terminal = true;

                child.val = commonString;
                child.terminal = false;
                child.children.clear();
                if (nodeChild.val.charAt(0) < textChild.val.charAt(0)) {
                    child.children.add(nodeChild);
                    child.children.add(textChild);
                } else {
                    child.children.add(textChild);
                    child.children.add(nodeChild);
                }
                needToAddLast = false;
                break;
            }
        }
        for (Map.Entry<Integer, SuffixNode> entry : modMap.entrySet()) {
            node.children.add(entry.getKey(), entry.getValue());
        }
        //  如果需要加在末尾，那么就加在末尾（唯一的continue 一路下去直到循环结束才会运行到此）
        if (needToAddLast) {
            SuffixNode addLastNode = new SuffixNode(text);
            addLastNode.terminal = true;
            node.children.add(addLastNode);
        }
    }

    // 获取最长公共字符串
    public String findLCS() {
        if (root != null) {
            return findLCS(root);
        }
        return "";
    }

    // 利用后缀字符串获取公共字符串的思路如下：
    // 对 bananas  anal  canal 而言，我们把输入字符串定为 text = "bananas#anal&canal@" terminal[] = ["#","&","@"]
    //  因为# & @ 三者都有且只有一个，那么包含中止符的(尤其是#)后续的字符串的情况只有一个，因此后缀字符串一定存在于子叶节点中
    //  因此，我们认为，包含三个字符的子叶节点所有父节点的元素相加即为最大公共字符串
    private String findLCS(SuffixNode curNode) {
        // 最大长度，当长度大于此最大长度时，即更新此值，并且继续扩展公共字符串
        // 在循环中对不同的子节点而言，length可能各不相同,需要一个在循环之外的定值确定最大值。
        int maxLength = curNode.val.length();
        // 定值，当前的节点长度
        int curNodeLength = curNode.val.length();
        // 返回值
        StringBuilder lcs = new StringBuilder();
        for (int i = 0; i < curNode.children.size(); i++) {
            SuffixNode child = curNode.children.get(i);
            // 针对某个子节点内部的长度
            int length = child.val.length() + curNodeLength;
            // 该节点下是否包含所有的中止符，如果是，则表示其所有父节点所包含元素之和都是公共字符串
            boolean containsAllTerminators = containsAllTerminators(child);
            // 生成最大字符串一切都是以包含所有中止符的子叶结点为前提的。
            if (containsAllTerminators) {
                // 真正合成分为三部分， 1 子节点 child 2 孙节点，通过递归完成  (父节点不用加，如果是root本身为空，递归的情况在递归之前已经加过了)
                // 以下是前两步
                if (length > maxLength) {
                    lcs = new StringBuilder(child.val);
                    maxLength = length;
                }
                // 以下是第三步
                String subLcs = findLCS(child);
                if (subLcs.length() > 0) {
                    length += subLcs.length();
                    if (length > maxLength) {
                        maxLength = length;
                        lcs.append(subLcs);
                    }
                }
            }
        }
        return lcs.toString();
    }

    private boolean containsAllTerminators(SuffixNode node) {
        boolean[] contains = new boolean[terminals.length];
        for (int i = 0; i < contains.length; i++) {
            contains[i] = false;
        }
        return containsAllTerminators(node, contains);
    }


    private boolean containsAllTerminators(SuffixNode node, boolean[] contains) {
        // 出于谨慎，默认并不都包含
        boolean allContains = false;
        // 因为我们只找字符串末尾，我们前面说过，字符串末尾不一定是子叶节点，但是子叶节点一定是字符串末尾
        // 反过来说，非字符串末尾，一定不是子叶节点。(另外，不可能存在含有中止符的非子叶节点)
        for (int i = 0; i < node.children.size(); i++) {
            SuffixNode child = node.children.get(i);
            //  通过递归找寻所有子叶节点，只要有子叶节点包含某个中止符，
            // 那么设置contains[j]=true 循环结束，所有元素都为true意味所有中止符都包含
            if (child.terminal) {
                for (int j = 0; j < contains.length; j++) {
                    if (child.val.contains(String.valueOf(terminals[j]))) {
                        contains[j] = true;
                        break;
                    }
                }
            } else {
                containsAllTerminators(child, contains);
            }

            // 这里是轮询需要
            allContains = true;
            for (boolean contain : contains) {
                if (!contain) {
                    allContains = false;
                }
            }

        }
        return allContains;

    }
}
