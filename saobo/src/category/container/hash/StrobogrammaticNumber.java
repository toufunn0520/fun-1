package category.container.hash;

import java.util.HashMap;
import java.util.Map;

public class StrobogrammaticNumber {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    /**
     * [Leetcode 246] https://leetcode.com/problems/strobogrammatic-number/
     * 
     * <pre>
     * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down). Write
     * a function to determine if a number is strobogrammatic. The number is represented as a string. For example, the
     * numbers "69", "88", and "818" are all strobogrammatic.
     * </pre>
     *
     * @param num
     * @return
     */
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) {
            return true;
        }

        Map<Character, Character> strobogrammaticMap = new HashMap<Character, Character>();
        strobogrammaticMap.put('6', '9');
        strobogrammaticMap.put('0', '0');
        strobogrammaticMap.put('1', '1');
        strobogrammaticMap.put('8', '8');
        strobogrammaticMap.put('9', '6');

        int i = 0, j = num.length() - 1;
        while (i <= j) {
            char left = num.charAt(i++);
            char right = num.charAt(j--);

            if (!strobogrammaticMap.containsKey(left) || right != strobogrammaticMap.get(left)) {
                return false;
            }
        }

        return true;
    }

}
