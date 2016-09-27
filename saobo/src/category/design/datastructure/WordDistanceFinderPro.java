package category.design.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordDistanceFinderPro implements WordDistanceFinder {

    /**
     * Indicates the minimum difference in times when we call size A is much larger than size B.
     */
    public static final int MUCH_LARGER_FACTOR = 2;

    public static void main(String[] args) {
        List<String> words = Arrays.asList("the", "quick", "brown", "fox", "quick");
        WordDistanceFinder finder = new WordDistanceFinderPro(words);
        System.out.println(finder.getMinDistance("the", "fox"));
        System.out.println(finder.getMinDistance("quick", "fox"));

    }

    private Map<String, List<Integer>> indiceMap;

    public WordDistanceFinderPro(List<String> wordsInput) {
        if (wordsInput == null) {
            throw new RuntimeException("Input words cannot be null.");
        }

        List<String> words = new ArrayList<String>(wordsInput);
        indiceMap = new HashMap<String, List<Integer>>();

        for (int i = 0; i < words.size(); i++) {
            String currentWord = words.get(i);

            List<Integer> indice = null;
            if (indiceMap.containsKey(currentWord)) {
                indice = indiceMap.get(currentWord);
            } else {
                indice = new ArrayList<Integer>();
                indiceMap.put(currentWord, indice);
            }
            indice.add(i);
        }
    }

    private int getDistanceByBinarySearch(List<Integer> list1, List<Integer> list2) {
        int index2 = 0;

        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < list2.size(); i++) {
            index2 = list2.get(i);

            int jStart = 0, jEnd = list1.size() - 1;

            int currentDistance = Math.abs(list1.get(jStart) - index2);
            distance = Math.min(distance, currentDistance);
            currentDistance = Math.abs(list1.get(jEnd) - index2);
            distance = Math.min(distance, currentDistance);

            while (jStart + 1 < jEnd) {
                int jMid = jStart + (jEnd - jStart) / 2;
                int midIndex1 = list1.get(jMid);

                currentDistance = Math.abs(midIndex1 - index2);
                distance = Math.min(distance, currentDistance);

                if (midIndex1 == index2) {
                    break;
                } else if (midIndex1 < index2) {
                    jStart = jMid;
                } else {
                    jEnd = jMid;
                }
            }
        }

        return distance;
    }

    /**
     * Worst case time complexity: O(m + n) Best case time complexity: O(Min(m, n))
     *
     * @param list1
     * @param list2
     * @return
     */
    private int getDistanceByMerge(List<Integer> list1, List<Integer> list2) {
        int i = 0, j = 0;
        int distance = Integer.MAX_VALUE;
        while (i < list1.size() && j < list2.size()) {
            int index1 = list1.get(i);
            int index2 = list2.get(j);

            distance = Math.min(distance, Math.abs(index1 - index2));
            if (index1 == index2) {
                break;
            } else if (index1 < index2) {
                i++;
            } else {
                j++;
            }
        }

        return distance;
    }

    @Override
    public int getMinDistance(String word1, String word2) {
        List<Integer> indices1 = indiceMap.get(word1);
        List<Integer> indices2 = indiceMap.get(word2);

        if (indices1 == null || indices2 == null || indices1.size() == 0 || indices2.size() == 0) {
            return -1;
        }

        // list1 is the indices has larger size.
        List<Integer> list1 = indices1.size() >= indices2.size() ? indices1 : indices2;
        List<Integer> list2 = indices1.size() >= indices2.size() ? indices2 : indices1;

        if (isSizeMuchLarger(list1, list2)) {
            return getDistanceByBinarySearch(list1, list2);
        } else {
            return getDistanceByMerge(list1, list2);
        }
    }

    private boolean isSizeMuchLarger(List<Integer> list1, List<Integer> list2) {
        return list1.size() / list2.size() > MUCH_LARGER_FACTOR;
    }
}
