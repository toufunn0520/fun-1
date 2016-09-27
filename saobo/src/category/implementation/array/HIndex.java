package category.implementation.array;

public class HIndex {

    public static void main(String[] args) {
        int[] citations = { 0 };

        System.out.println(new HIndex().hIndex(citations));
    }

    /**
     * [Leetcode 274] https://leetcode.com/problems/h-index/
     *
     * <pre>
     * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to
     * compute the researcher's h-index. According to the definition of h-index on Wikipedia:
     * "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no
     * more than h citations each."
     * For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them
     * had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations
     * each and the remaining two with no more than 3 citations each, his h-index is 3. Note: If there are several
     * possible values for h, the maximum one is taken as the h-index.
     * </pre>
     *
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }

        int len = citations.length;

        int[] stastics = new int[len + 1];
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] > len) {
                stastics[len]++;
            } else {
                stastics[citations[i]]++;
            }
        }

        int sum = 0;
        for (int i = len; i >= 0; i--) {
            sum += stastics[i];

            if (sum >= i) {
                return i;
            }
        }

        return 0;
    }

}
