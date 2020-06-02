//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 示例: 
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// 说明: 
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。 
// Related Topics 字符串 回溯算法


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Java：电话号码的字母组合
public class P17LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new P17LetterCombinationsOfAPhoneNumber().new Solution();
        // TO TEST
        List<String> result = solution.letterCombinations("234");
        for(String s : result){
            System.out.println(s + "\n");
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> letterCombinations(String digits) {
            Character[][] plate = new Character[][]{{'a', 'b', 'c'}, {'d', 'e', 'f'}
                    , {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'p'}
                    , {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
            HashMap<Character, Character[]> hashMap = new HashMap<>();
            for (int i = 2; i <= 9; i++) {
                hashMap.put((char) (i + '0'), plate[i - 2]);
            }
            List<Character[]> charactersList = new ArrayList<>();
            List<String> stringList = new ArrayList<>();
            for (Character c : digits.toCharArray()) {
                charactersList.add(hashMap.get(c));
            }
            completeListString(stringList, charactersList);
            return stringList;
        }

        private void completeListString(List<String> result, List<Character[]> charactersList) {
            if (charactersList.size() > 0) {
                Character[] characters = charactersList.get(0);
                if (result.size() == 0) {
                    for(Character c : characters){
                        result.add(String.valueOf(c));
                    }
                } else {
                    List<String> newResult = new ArrayList<>();
                    for(String s : result){
                        for(Character c : characters){
                            newResult.add(s + c);
                        }
                    }
                    result.clear();
                    result.addAll(newResult);
                }
                charactersList.remove(characters);
                completeListString(result, charactersList);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}