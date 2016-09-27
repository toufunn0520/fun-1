package category.implementation.string.operations;

public class CountAndSay {

    /**
     * [Leetcode 38] https://leetcode.com/problems/count-and-say/
     * 
     * <pre>
     * The count-and-say sequence is the sequence of integers beginning as follows:
     * 1, 11, 21, 1211, 111221, ...
     * 
     * 1 is read off as "one 1" or 11.
     * 11 is read off as "two 1s" or 21.
     * 21 is read off as "one 2, then one 1" or 1211.
     * Given an integer n, generate the nth sequence.
     * 
     * Note: The sequence of integers will be represented as a string.
     * </pre>
     * 
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append("1");
        for (int i = 1; i < n; i++) {
            String current = sb.toString();
            sb = new StringBuilder();
            for (int j = 0; j < current.length();) {
                char currentDigit = current.charAt(j);
                int count = 0;
                while (j < current.length() && current.charAt(j) == currentDigit) {
                    count++;
                    j++;
                }
                sb.append(count);
                sb.append(currentDigit);
            }
        }

        return sb.toString();
    }
}
