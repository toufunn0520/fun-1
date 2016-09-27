package category.dp.twosequences;

public class EditDistance {

    public static void main(String[] args) {
        System.out.println(new EditDistance().minDistance("word1", "woword1"));
    }

    /**
     * [Leetcode 72] https://leetcode.com/problems/edit-distance/
     * 
     * <pre>
     * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each
     * operation is counted as 1 step.) You have the following 3 operations permitted on a word:
     *
     * a) Insert a character
     * b) Delete a character
     * c) Replace a character
     * </pre>
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        if (word1 == null) {
            return word2 == null ? 0 : word2.length();
        }

        if (word2 == null) {
            return word1 == null ? 0 : word1.length();
        }

        int[][] distance = new int[word1.length() + 1][word2.length() + 1];
        distance[0][0] = 0;

        for (int i = 1; i <= word2.length(); i++) {
            distance[0][i] = i;
        }

        for (int i = 0; i <= word1.length(); i++) {
            distance[i][0] = i;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                distance[i][j] = Math.min(distance[i - 1][j], distance[i][j - 1]) + 1;
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    distance[i][j] = Math.min(distance[i][j], distance[i - 1][j - 1]);
                } else {
                    distance[i][j] = Math.min(distance[i][j], distance[i - 1][j - 1] + 1);
                }
            }
        }

        return distance[word1.length()][word2.length()];
    }
}
