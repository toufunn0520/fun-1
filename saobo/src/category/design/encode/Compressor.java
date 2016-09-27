package category.design.encode;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Facebook onsite: compress a string "aaabbc" to "3a2b1c". follow ups: what is there is numbers inside the string, how
 * to decompress.
 *
 * @author boyi
 */
public class Compressor {

    public static final char SPECIAL_CHAR = '#';

    private static void appendCurrentChar(StringBuilder sb, char currentChar, int count) {
        try {
            sb.append(count);
            Integer.parseUnsignedInt("" + currentChar);
            sb.append(SPECIAL_CHAR);
        } catch (NumberFormatException e) {

        }
        sb.append(currentChar);
    }

    public static String compress(String s) {
        if (s == null || s.length() == 0)
            return s;

        StringBuilder sb = new StringBuilder(s.length());
        char currentChar = s.charAt(0);
        int count = 1;
        int i = 0;

        while (++i < s.length()) {
            if (s.charAt(i) == currentChar) {
                count++;
            } else {
                appendCurrentChar(sb, currentChar, count);
                currentChar = s.charAt(i);
                count = 1;
            }
        }

        if (i == s.length()) {
            appendCurrentChar(sb, currentChar, count);
        }

        return sb.toString();
    }

    public static String decompress(String s) {
        if (s == null || s.length() == 0)
            return s;
        if (s.length() == 1)
            throw new IllegalArgumentException("length of the compressed string cannot be 1.");

        StringBuilder sb = new StringBuilder(s.length() * 2);

        for (int i = 0; i < s.length(); i++) {
            int startDigitIndex = i;
            while (++i < s.length()) {
                try {
                    Integer.parseUnsignedInt("" + s.charAt(i));
                } catch (NumberFormatException e) {
                    break;
                }
            }
            int endDigitIndex = i;
            int digit = 0;
            try {
                digit = Integer.parseUnsignedInt(s.substring(startDigitIndex, endDigitIndex));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "input compressed string is not valid: each part should start with number.");
            }

            if (i < s.length() && s.charAt(i) == SPECIAL_CHAR) {
                i++;
            }

            if (i == s.length()) {
                throw new IllegalArgumentException(
                        "input compressed string is not valid: each part should have character followed.");
            }

            char currentChar = s.charAt(i);
            for (int j = 0; j < digit; j++) {
                sb.append(currentChar);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(Compressor.decompress(Compressor.compress("a1111111aabbbbcccdddd")));
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testCompressWithEmptyString() {
        Assert.assertEquals("", Compressor.compress(""));
    }

    @Test
    public void testCompressWithNullString() {
        Assert.assertEquals(null, Compressor.compress(null));
    }

    @Test
    public void testCompressWithNumbers() {
        Assert.assertEquals("1a7#12a4b3c4d", Compressor.compress("a1111111aabbbbcccdddd"));
    }

    @Test
    public void testCompressWithOneCharacter() {
        Assert.assertEquals("1a", Compressor.compress("a"));
    }

    @Test
    public void testCompressWithOneNumber() {
        Assert.assertEquals("1#1", Compressor.compress("1"));
    }

    @Test
    public void testCompressWithOnlyCharacters() {
        Assert.assertEquals("3a", Compressor.compress("aaa"));
    }

    @Test
    public void testCompressWithOnlyNumbers() {
        Assert.assertEquals("7#1", Compressor.compress("1111111"));
    }

    @Test
    public void testDecompressWithEmptyString() {
        Assert.assertEquals("", Compressor.decompress(""));
    }

    @Test
    public void testDecompressWithInvalidEnding() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("input compressed string is not valid: each part should have character followed.");

        Compressor.decompress("7#");
    }

    @Test
    public void testDecompressWithInvalidEnding2() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("input compressed string is not valid: each part should have character followed.");

        Compressor.decompress("7#12");
    }

    @Test
    public void testDecompressWithInvalidStart() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("input compressed string is not valid: each part should start with number.");

        Compressor.decompress("3ab");
    }

    @Test
    public void testDecompressWithInvalidStartFromBeginning() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("input compressed string is not valid: each part should start with number.");

        Compressor.decompress("aaa");
    }

    @Test
    public void testDecompressWithNullString() {
        Assert.assertEquals(null, Compressor.decompress(null));
    }

    @Test
    public void testDecompressWithNumbers() {
        Assert.assertEquals("a1111111aabbbbcccdddd", Compressor.decompress("1a7#12a4b3c4d"));
    }

    @Test
    public void testDecompressWithOneCharacter() {
        Assert.assertEquals("a", Compressor.decompress("1a"));
    }

    @Test
    public void testDecompressWithOneNumber() {
        Assert.assertEquals("1", Compressor.decompress("1#1"));
    }

    @Test
    public void testDecompressWithOnlyCharacters() {
        Assert.assertEquals("aaa", Compressor.decompress("3a"));
    }

    @Test
    public void testDecompressWithOnlyNumbers() {
        Assert.assertEquals("1111111", Compressor.decompress("7#1"));
    }

    @Test
    public void testDecompressWithOnlyOneChar() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("length of the compressed string cannot be 1.");

        Compressor.decompress("7");
    }
}
