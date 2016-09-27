package category.implementation.string.parse;

import org.junit.Assert;
import org.junit.Test;

public class ValidNumber {

    public static void main(String[] args) {
        ValidNumber validator = new ValidNumber();
        System.out.println(validator.isNumber("84656e656D"));
    }

    /**
     * [Leetcode 65] https://leetcode.com/problems/valid-number/
     *
     * <pre>
     * Validate if a given string is numeric.
     *
     * Some examples:
     * "0" => true
     * " 0.1 " => true
     * "abc" => false
     * "1 a" => false
     * "2e10" => true
     * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
     * </pre>
     *
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }

        return validScientificNumber(s) || validFloat(s) || validInteger(s);
    }

    @Test
    public void test() {
        ValidNumber validator = new ValidNumber();
        Assert.assertEquals(true, validator.isNumber("0"));
        Assert.assertEquals(true, validator.isNumber("0.1"));
        Assert.assertEquals(false, validator.isNumber("abc"));
        Assert.assertEquals(false, validator.isNumber("1 a"));
        Assert.assertEquals(true, validator.isNumber("2e10"));
        Assert.assertEquals(true, validator.isNumber("0001"));
        Assert.assertEquals(false, validator.isNumber("e9"));
        Assert.assertEquals(false, validator.isNumber("84656e656D"));
        Assert.assertEquals(false, validator.isNumber("959440.94f"));
    }

    private boolean validFloat(String s) {
        if (s.isEmpty() || s.indexOf('f') != -1 || s.indexOf('D') != -1) {
            return false;
        } else {
            try {
                Double.parseDouble(s);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }
    }

    private boolean validInteger(String s) {
        if (s.isEmpty()) {
            return false;
        } else {
            try {
                Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }
    }

    private boolean validScientificNumber(String s) {
        int index = s.indexOf('e');
        if (index == -1 || index == s.length() - 1) {
            return false;
        }

        String base = s.substring(0, index);
        String exponent = s.substring(index + 1, s.length());
        return (validInteger(base) && validInteger(exponent));
    }
}
