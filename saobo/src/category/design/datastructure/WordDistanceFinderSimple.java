package category.design.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordDistanceFinderSimple implements WordDistanceFinder {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("the", "quick", "brown", "fox", "quick");
        WordDistanceFinder finder = new WordDistanceFinderSimple(words);
        System.out.println(finder.getMinDistance("the", "fox"));
        System.out.println(finder.getMinDistance("quick", "fox"));

    }

    private List<String> words;

    public WordDistanceFinderSimple(List<String> words) {
        if (words == null) {
            throw new RuntimeException("Input words cannot be null.");
        }

        this.words = new ArrayList<String>(words);
    }

    @Override
    public int getMinDistance(String word1, String word2) {
        int index1 = -1, index2 = -1, distance = Integer.MAX_VALUE;

        for (int i = 0; i < words.size(); i++) {
            String currentWord = words.get(i);

            if (currentWord.equals(word1)) {
                index1 = i;
                if (index2 >= 0) {
                    distance = Math.min(distance, index1 - index2);
                }
            }

            if (currentWord.equals(word2)) {
                index2 = i;
                if (index1 >= 0) {
                    distance = Math.min(distance, index2 - index1);
                }
            }
        }

        return (index1 < 0 || index2 < 0) ? -1 : distance;
    }

}
