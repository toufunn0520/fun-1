package category.container.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * [Google onsite]: 有两个string， 比如 s1 = "abc", s2 = "cba",相同index下的字母不同，我 们叫一个difference，比如在index 0 上 s1是 a 而s2 是
 * c，这就是一个difference， 而index 1 上 s1和s2都是b，则不是difference.现在只许你swap一次 S2 的两个字母，问如何才能 最大程度的减少difference， 需要return
 * swap的两个index
 *
 * @author boyi
 */
public class MinDifferenceAfterOneSwap {

    public static void main(String[] args) {
        int[] result = new MinDifferenceAfterOneSwap().getSwapIndexes("abacanm", "mancbmn");
        System.out.println(result[0]);
        System.out.println(result[1]);

    }

    private void addToMap(Map<Character, Set<Character>> c2cMap, Character keyChar, Character valueChar) {
        if (c2cMap.containsKey(keyChar)) {
            c2cMap.get(keyChar).add(valueChar);
        } else {
            Set<Character> charSet = new HashSet<Character>();
            charSet.add(valueChar);
            c2cMap.put(keyChar, charSet);
        }
    }

    public int[] getSwapIndexes(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return null;
        }

        int len = s1.length();

        Map<Character, Set<Character>> unMatchedS2ToS1 = new HashMap<Character, Set<Character>>();
        Map<Character, Set<Character>> unMatchedS1ToS2 = new HashMap<Character, Set<Character>>();
        Set<Character> swapTemp1 = null;
        Set<Character> swapTemp2 = null;
        int[] swapIndexes = new int[2];

        for (int i = 0; i < len; i++) {
            Character charAtS2 = s2.charAt(i);
            Character charAtS1 = s1.charAt(i);

            if (charAtS1 != charAtS2) {
                if (unMatchedS2ToS1.containsKey(charAtS1)) {
                    swapTemp1 = unMatchedS2ToS1.get(charAtS1);
                    swapIndexes[1] = i;

                    if (unMatchedS2ToS1.get(charAtS1).contains(charAtS2)) {
                        swapTemp2 = null;
                        break;
                    }
                } else if (unMatchedS1ToS2.containsKey(charAtS2)) {
                    swapTemp2 = unMatchedS1ToS2.get(charAtS2);
                    swapIndexes[1] = i;

                    if (unMatchedS1ToS2.get(charAtS2).contains(charAtS1)) {
                        swapTemp1 = null;
                        break;
                    }
                }

                addToMap(unMatchedS2ToS1, charAtS2, charAtS1);
                addToMap(unMatchedS1ToS2, charAtS1, charAtS2);
            }
        }

        for (int i = 0; i <= len; i++) {
            if (swapTemp1 != null && swapTemp1.contains(s1.charAt(i))) {
                swapIndexes[0] = i;
                break;
            } else if (swapTemp2 != null && swapTemp2.contains(s2.charAt(i))) {
                swapIndexes[0] = i;
                break;
            }
        }
        return swapIndexes;
    }
}
