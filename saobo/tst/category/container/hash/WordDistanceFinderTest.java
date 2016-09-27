package category.container.hash;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import category.design.datastructure.WordDistanceFinder;
import category.design.datastructure.WordDistanceFinderPro;
import category.design.datastructure.WordDistanceFinderSimple;

public class WordDistanceFinderTest {

    private long getExcutionTime(WordDistanceFinder finder, String[] words1, String[] words2) {
        long start = System.nanoTime();
        for (int i = 0; i < words1.length; i++) {
            finder.getMinDistance(words1[i], words2[i]);
            finder.getMinDistance(words2[i], words1[i]);
        }
        long end = System.nanoTime();

        return end - start;
    }

    @Test
    public void testBasicCase() {
        List<String> words = Arrays.asList("the", "quick", "brown", "fox", "quick");

        String[] words1 = { "fox", "quick" };
        String[] words2 = { "the", "fox" };
        int[] distances = { 3, 1 };

        testGetDistanceWithInputs(words, words1, words2, distances);
    }

    private void testGetDistanceWithInputs(List<String> words, String[] words1, String[] words2, int[] expectedDistance) {
        WordDistanceFinder proFinder = new WordDistanceFinderPro(words);
        WordDistanceFinder simpleFinder = new WordDistanceFinderSimple(words);

        for (int i = 0; i < words1.length; i++) {
            Assert.assertEquals(expectedDistance[i], simpleFinder.getMinDistance(words1[i], words2[i]));
            Assert.assertEquals(expectedDistance[i], proFinder.getMinDistance(words1[i], words2[i]));
            Assert.assertEquals(expectedDistance[i], simpleFinder.getMinDistance(words2[i], words1[i]));
            Assert.assertEquals(expectedDistance[i], proFinder.getMinDistance(words2[i], words1[i]));
        }
    }

    @Test
    public void testPerformanceWithBigInput() {
        List<String> words = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
                "p", "q", "a", "r", "s", "t", "u", "v", "w", "x", "y", "z", "a", "b", "c", "a", "d", "x", "e", "f",
                "g", "h", "i", "a", "j", "k", "l", "m", "n", "o", "p", "q", "r", "a", "s", "t", "u", "v", "w", "a",
                "x", "y", "a", "z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
                "q", "a", "r", "s", "t", "u", "v", "w", "x", "y", "z", "a", "b", "c", "a", "d", "x", "e", "f", "g",
                "h", "i", "a", "j", "k", "l", "m", "n", "o", "p", "q", "r", "a", "s", "t", "u", "v", "w", "a", "x",
                "y", "a", "z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
                "a", "r", "s", "t", "u", "v", "w", "x", "y", "z", "a", "b", "c", "a", "d", "x", "e", "f", "g", "h",
                "i", "a", "j", "k", "l", "m", "n", "o", "p", "q", "r", "a", "s", "t", "u", "v", "w", "a", "x", "y",
                "a", "z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "a",
                "r", "s", "t", "u", "v", "w", "x", "y", "z", "a", "b", "c", "a", "d", "x", "e", "f", "g", "h", "i",
                "a", "j", "k", "l", "m", "n", "o", "p", "q", "r", "a", "s", "t", "u", "v", "w", "a", "x", "y", "a",
                "z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "a", "r",
                "s", "t", "u", "v", "w", "x", "y", "z", "a", "b", "c", "a", "d", "x", "e", "f", "g", "h", "i", "a",
                "j", "k", "l", "m", "n", "o", "p", "q", "r", "a", "s", "t", "u", "v", "w", "a", "x", "y", "a", "z",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "a", "r", "s",
                "t", "u", "v", "w", "x", "y", "z", "a", "b", "c", "a", "d", "x", "e", "f", "g", "h", "i", "a", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "a", "s", "t", "u", "v", "w", "a", "x", "y", "a", "z");

        String[] words1 = { "a", "a", "a", "a", "a", "a", "aa", "a", "a", "a", "a", "a" };
        String[] words2 = { "o", "z", "f", "i", "o", "x", "o", "zz", "f", "i", "o", "x" };
        WordDistanceFinder proFinder = new WordDistanceFinderPro(words);
        WordDistanceFinder simpleFinder = new WordDistanceFinderSimple(words);

        long proExecutionTime = getExcutionTime(proFinder, words1, words2);
        long simpleExecutionTime = getExcutionTime(simpleFinder, words1, words2);

        Assert.assertTrue(proExecutionTime < simpleExecutionTime);
    }

    @Test
    public void testWhenOneCannotFound() {
        List<String> words = Arrays.asList("the", "quick", "brown", "fox", "quick");

        String[] words1 = { "foxa", "quick", "abc" };
        String[] words2 = { "the", "foxa", "efs" };
        int[] distances = { -1, -1, -1 };

        testGetDistanceWithInputs(words, words1, words2, distances);
    }

    @Test
    public void testWithBigInput() {
        List<String> words = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
                "p", "q", "a", "r", "s", "t", "u", "v", "w", "x", "y", "z", "a", "b", "c", "a", "d", "x", "e", "f",
                "g", "h", "i", "a", "j", "k", "l", "m", "n", "o", "p", "q", "r", "a", "s", "t", "u", "v", "w", "a",
                "x", "y", "a", "z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
                "q", "a", "r", "s", "t", "u", "v", "w", "x", "y", "z", "a", "b", "c", "a", "d", "x", "e", "f", "g",
                "h", "i", "a", "j", "k", "l", "m", "n", "o", "p", "q", "r", "a", "s", "t", "u", "v", "w", "a", "x",
                "y", "a", "z");

        String[] words1 = { "a", "a", "x", "h", "aaa", "o" };
        String[] words2 = { "o", "z", "y", "l", "a", "bb" };
        int[] distances = { 3, 1, 1, 4, -1, -1 };

        testGetDistanceWithInputs(words, words1, words2, distances);
    }

}
