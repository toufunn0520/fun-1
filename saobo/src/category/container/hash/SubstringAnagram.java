package category.container.hash;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class SubstringAnagram {

    /**
     * [Snap*chat] Given a string s and a target, determine if the target is an anagram of one of the substrings of s.
     *
     * @param s
     * @param target
     * @return
     */
    public boolean containsSubstringAnagram(String s, String target) {
        if (target == null || s == null || s.length() == 0) {
            return false;
        }

        Map<Character, Integer> charset = new HashMap<Character, Integer>();
        for (char c : target.toCharArray()) {
            if (charset.containsKey(c)) {
                charset.put(c, charset.get(c) + 1);
            } else {
                charset.put(c, 1);
            }
        }

        char[] sCharArray = s.toCharArray();
        for (int i = 0; i < sCharArray.length; i++) {
            if (!charset.containsKey(sCharArray[i])) {
                continue;
            } else {
                int startIndex = i;
                Map<Character, Integer> copyCharset = new HashMap<Character, Integer>(charset);
                do {
                    if (copyCharset.containsKey(sCharArray[i])) {
                        int count = copyCharset.get(sCharArray[i]);
                        if (count == 1) {
                            copyCharset.remove(sCharArray[i]);
                            if (copyCharset.size() == 0) {
                                return true;
                            }

                        } else {
                            copyCharset.put(sCharArray[i], count - 1);
                        }
                    } else {
                        i = startIndex;
                        break;
                    }
                } while (++i < sCharArray.length);
            }
        }

        return false;
    }

    @Test
    public void testWithDifferentInputs() {
        SubstringAnagram detector = new SubstringAnagram();

        Assert.assertEquals(true, detector.containsSubstringAnagram("a", "a"));
        Assert.assertEquals(true, detector.containsSubstringAnagram("abc", "a"));
        Assert.assertEquals(true, detector.containsSubstringAnagram("abac", "bcaa"));
        Assert.assertEquals(true, detector.containsSubstringAnagram("xyzabac", "bcaa"));
        Assert.assertEquals(true, detector.containsSubstringAnagram("abacxyz", "bcaa"));
        Assert.assertEquals(true, detector.containsSubstringAnagram("xyzabacxyz", "bcaa"));
        Assert.assertEquals(false, detector.containsSubstringAnagram("xyzabbacxyz", "bcaa"));
        Assert.assertEquals(false, detector.containsSubstringAnagram("", "abc"));
        Assert.assertEquals(false, detector.containsSubstringAnagram("abc", null));
        Assert.assertEquals(false, detector.containsSubstringAnagram("abc", ""));
    }
}
