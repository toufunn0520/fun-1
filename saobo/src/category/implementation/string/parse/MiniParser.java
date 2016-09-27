package category.implementation.string.parse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implement a mini parser, input is a string "[123,456,[788,799,833],[[]],10,[]]", output is a structure:
 * "[123,456,[788,799,833],[[]],10,[]]".
 *
 * @author boyi
 */
public class MiniParser {

    public static Numbers getNumbers(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }

        return getNumbersHelper(input);
    }

    private static Numbers getNumbersHelper(String s) {
        try {
            int i = Integer.parseInt(s);
            return new Numbers(i);
        } catch (NumberFormatException e) {

        }

        List<Numbers> list = new ArrayList<Numbers>();
        Numbers result = new Numbers(list);

        if (s.length() <= 2) {
            return result;
        }

        String[] chars = splitByComma(s);
        for (int i = 0; i < chars.length; i++) {
            list.add(getNumbersHelper(chars[i]));
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(getNumbers("324"));

    }

    public static String[] splitByComma(String s) {

        s = s.substring(1, s.length() - 1);

        String[] chunks = s.split(",");

        int dataIndex = 0;
        int countOfOpenPathensis = 0;
        for (int i = 0; i < chunks.length;) {
            if (!chunks[i].startsWith("[") || chunks[i].endsWith("]")) {
                chunks[dataIndex++] = chunks[i++];
            } else {
                countOfOpenPathensis++;

                chunks[dataIndex] = chunks[i++];
                while (countOfOpenPathensis > 0) {

                    if (chunks[i].endsWith("]")) {
                        countOfOpenPathensis--;
                    } else {
                        if (chunks[i].startsWith("[")) {
                            countOfOpenPathensis++;
                        }
                    }
                    chunks[dataIndex] += "," + chunks[i++];

                    if (countOfOpenPathensis == 0) {
                        dataIndex++;
                    }
                }

            }
        }

        return Arrays.copyOf(chunks, dataIndex);
    }

}

class Numbers {

    boolean isNumber;

    List<Numbers> numbers;

    int val;

    public Numbers(int val) {
        this.isNumber = true;
        this.val = val;
    }

    public Numbers(List<Numbers> list) {
        this.isNumber = false;
        this.numbers = list;
    }

    @Override
    public String toString() {
        return isNumber ? String.valueOf(val) : numbers.toString();
    }
}
