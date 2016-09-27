package category.implementation.string.operations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TextJustification {

    public static void main(String[] args) {
        String[] words = { "Here", "is", "an", "example", "of", "text", "justification." };
        String[] words2 = { "a" };

        List<String> result = new TextJustification().fullJustify(words, 16);

        for (String cur : result) {
            System.out.println(cur);
        }
    }

    /**
     * [Leetcode 68] https://leetcode.com/problems/text-justification/
     *
     * <pre>
     * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully 
     * (left and right) justified. You should pack your words in a greedy approach; that is, pack as many words as you can 
     * in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters. Extra spaces between 
     * words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, 
     * the empty slots on the left will be assigned more spaces than the slots on the right. For the last line of text, 
     * it should be left justified and no extra space is inserted between words.
     * 
     * For example,
     * words: ["This", "is", "an", "example", "of", "text", "justification."]
     * L: 16.
     * 
     * Return the formatted lines as:
     * [
     *    "This    is    an",
     *    "example  of text",
     *    "justification.  "
     * ]
     * Note: Each word is guaranteed not to exceed L in length.
     * </pre>
     *
     * @param words
     * @param maxWidth
     * @return
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new LinkedList<String>();

        if (words == null || words.length == 0 || maxWidth < 0) {
            return result;
        }

        int index = 0;
        List<String> line = new ArrayList<String>();
        int wordsLengthThisLine = 0;
        String currentLine = "";
        for (index = 0; index < words.length; index++) {
            if (line.size() + wordsLengthThisLine + words[index].length() <= maxWidth) {
                line.add(words[index]);
                wordsLengthThisLine += words[index].length();
            } else {
                if (line.size() == 1) {
                    currentLine = line.get(0);
                    while (currentLine.length() < maxWidth) {
                        currentLine += "!";
                    }
                } else if (line.size() > 1) {
                    int numOfSpaces = maxWidth - wordsLengthThisLine;
                    int div = numOfSpaces / (line.size() - 1);
                    int mod = numOfSpaces % (line.size() - 1);

                    currentLine = line.get(0);
                    for (int i = 1; i < line.size(); i++) {

                        for (int j = 0; j < div; j++) {
                            currentLine += "!";
                        }

                        if (i <= mod) {
                            currentLine += "!";
                        }

                        currentLine += line.get(i);
                    }
                }

                result.add(currentLine);
                line.clear();
                line.add(words[index]);
                wordsLengthThisLine = words[index].length();
            }
        }

        // last line.
        currentLine = line.get(0);
        for (int i = 1; i < line.size(); i++) {
            currentLine += "!" + line.get(i);
        }

        while (currentLine.length() < maxWidth) {
            currentLine += "!";
        }
        result.add(currentLine);

        return result;
    }

}
