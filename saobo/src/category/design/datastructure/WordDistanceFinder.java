package category.design.datastructure;

public interface WordDistanceFinder {

    /**
     * Linkedin onsite: Please design and implement a class: This class will be given a list of words (such as might be
     * tokenized from a paragraph of text), and will provide a method that takes two words and returns the shortest
     * distance (in words) between those two words in the provided text.
     *
     * @param word1
     * @param word2
     * @return the minimum distance between the two words.
     */
    public int getMinDistance(String word1, String word2);
}
