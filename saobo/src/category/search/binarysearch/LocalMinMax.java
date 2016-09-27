package category.search.binarysearch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Facebook onsite: Given an array of integers, the neighbor elements always larger or smaller than the previous one
 * within difference 1. i.e [8 9 10] or [4,3,3] If an element is larger than previous neighbor and larger than later
 * neighbor, it is a local max. If an element is smaller than the previous neighbor and smaller than the later one, it
 * is called local min. Given an array, try to find local min and max.
 * 
 * @author boyi
 */
public class LocalMinMax {

    public static void main(String[] args) {
        int[] testData = { 8, 8, 9, 8, 7, 8 };

        System.out.println(findLocalMinMax(testData));
    }

    /**
     * Similar to Binary search. Time complexity: O(lgN) on average case. O(N) on worst case.
     * 
     * @param input
     * @return
     */
    public static Map<MinMaxType, List<Integer>> findLocalMinMax(int[] input) {
        if (input == null)
            return null;
        if (input.length <= 2)
            return null;

        List<Integer> minList = new LinkedList<Integer>();
        List<Integer> maxList = new LinkedList<Integer>();
        Map<MinMaxType, List<Integer>> result = new HashMap<MinMaxType, List<Integer>>();
        result.put(MinMaxType.Max, maxList);
        result.put(MinMaxType.Min, minList);
        return findHelper(input, 0, input.length - 1, result);
    }

    private static Map<MinMaxType, List<Integer>> findHelper(int[] input, int startIndex, int endIndex,
            Map<MinMaxType, List<Integer>> result) {
        if (endIndex - startIndex == 2) {
            if (input[startIndex] < input[startIndex + 1] && input[startIndex + 1] > input[endIndex]) {
                result.get(MinMaxType.Max).add(startIndex + 1);
            } else if (input[startIndex] > input[startIndex + 1] && input[startIndex + 1] < input[endIndex]) {
                result.get(MinMaxType.Min).add(startIndex + 1);
            }
        } else {
            if (!isMonotonous(input, startIndex, endIndex)) {
                findHelper(input, startIndex, (endIndex + startIndex) / 2 + 1, result);
                findHelper(input, (endIndex + startIndex) / 2, endIndex, result);
            }
        }
        return result;
    }

    private static boolean isMonotonous(int[] array, int startIndex, int endIndex) {
        if (array == null)
            return true;

        int length = endIndex - startIndex + 1;

        if (array[endIndex] > array[startIndex] + length || array[endIndex] < array[startIndex] - length) {
            return true;
        } else {
            return false;
        }

    }

    public enum MinMaxType {
        Min, Max
    }
}
