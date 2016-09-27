package category.search.binarysearch;

public class HIndex2 {

    /**
     * [Leetcode 275] https://leetcode.com/problems/h-index-ii/ What if the citations array is sorted in ascending
     * order? Could you optimize your algorithm?
     *
     * @param citations
     * @return
     */
    public int hIndex2(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }

        int len = citations.length;

        int left = 0, right = len - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (len - mid > citations[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return (len - right) < citations[right] ? (len - right) : citations[right];
    }
}
