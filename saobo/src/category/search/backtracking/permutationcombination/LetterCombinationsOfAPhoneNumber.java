package category.search.backtracking.permutationcombination;

import java.util.LinkedList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {

    private static final String[] LETTERS = { "", // 0
        "", // 1
        "abc", // 2
        "def", // 3
        "ghi", // 4
        "jkl", // 5
        "mno", // 6
        "pqrs", // 7
        "tuv", // 8
        "wxyz" // 9
    };

    public static void main(String[] args) {

        List<String> result = new LetterCombinationsOfAPhoneNumber().letterCombinations("22");
        for (int i = 0; i < result.size(); i++)
            System.out.println(result.get(i));

    }

    public void helper(String digits, String combination, List<String> combinations) {
        if (digits.isEmpty()) {
            combinations.add(combination);
            return;
        }

        int currentDigit = digits.charAt(0) - '0';

        if (currentDigit > 9 || currentDigit < 0 || LETTERS[currentDigit].isEmpty()) {
            combinations.add(combination);
            return;
        }

        for (int i = 0; i < LETTERS[currentDigit].length(); i++) {
            helper(digits.substring(1), combination + LETTERS[currentDigit].charAt(i), combinations);
        }
    }

    /**
     * [Leetcode 17] https://leetcode.com/problems/letter-combinations-of-a-phone-number/
     *
     * <pre>
     * Given a digit string, return all possible letter combinations that the number could represent.
     *
     * A mapping of digit to letters (just like on the telephone buttons) is given below.
     *
     * Input:Digit string "23"
     * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * </pre>
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new LinkedList<String>();

        if (digits != null && !digits.isEmpty()) {
            helper(digits, "", combinations);
        }

        return combinations;
    }
}
