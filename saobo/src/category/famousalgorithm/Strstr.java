package category.famousalgorithm;

/**
 * [Leetcode 28] https://leetcode.com/problems/implement-strstr/
 *
 * <pre>
 * Implement strStr().
 *
 * Returns the index of the first occurrence of needle in haystack, or -1 if
 * needle is not part of haystack.
 * </pre>
 *
 * @author boyi
 */
public class Strstr {

    public static void main(String[] args) {
        System.out.println(new Strstr().strStr("bbc abcdab abcdabcdabde", "abcdabd"));
    }

    // calculate KMP array
    public int[] getNext(String needle) {
        int[] next = new int[needle.length()];
        int pLen = needle.length();
        next[0] = -1;

        int k = -1;
        int j = 0;

        while (j < pLen - 1) {
            if (k == -1 || needle.charAt(j) == needle.charAt(k)) {
                ++k;
                ++j;
                if (needle.charAt(j) != needle.charAt(k)) {
                    next[j] = k;
                } else {
                    next[j] = next[k];
                }

            } else {
                k = next[k];
            }
        }

        return next;
    }

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null)
            return 0;

        int hLength = haystack.length();
        int nLength = needle.length();

        if (nLength > hLength)
            return -1;
        if (nLength == 0)
            return 0;

        int[] next = getNext(needle);
        int haystackIndex = 0, needleIndex = 0;

        while (haystackIndex < hLength && needleIndex < nLength) {
            if (needleIndex == -1 || haystack.charAt(haystackIndex) == needle.charAt(needleIndex)) {
                haystackIndex++;
                needleIndex++;
            } else {
                needleIndex = next[needleIndex];
            }
        }

        if (needleIndex == nLength) {
            return haystackIndex - needleIndex;
        } else {
            return -1;
        }
    }
}
