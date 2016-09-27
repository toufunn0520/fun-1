package category.design.encode;

import java.util.LinkedList;
import java.util.List;

public class CompressorWithoutSpecialChar {

    static final char DELEMETER = '8';

    public static List<Character> compress(String s) {
        List<Character> list = new LinkedList<Character>();

        if (s == null || s.length() == 0) {
            return list;
        }

        int i = 0;
        while (i < s.length()) {
            char current = s.charAt(i);
            int count = 1;
            while (++i < s.length() && s.charAt(i) == current) {
                count++;
            }

            if (count > 2) {
                list.add(DELEMETER);
                list.add((char) ('0' + count));
                list.add(current);
            } else if (count == 2) {
                list.add(current);
                list.add(current);
            } else {
                list.add(current);
                if (current == DELEMETER) {
                    list.add('1');
                }
            }
        }

        return list;
    }

    public static String decode(List<Character> encodedList) {
        if (encodedList == null || encodedList.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encodedList.size(); i++) {
            if (encodedList.get(i) == DELEMETER) {
                if (i < encodedList.size() - 1 && encodedList.get(i + 1) == '1') {
                    sb.append(DELEMETER);
                    i++;
                } else {
                    char current = encodedList.get(i + 2);
                    for (int j = 0; j < encodedList.get(i + 1) - '0'; j++) {
                        sb.append(current);
                    }
                    i += 2;
                }
            } else {
                sb.append(encodedList.get(i));
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "1122238884444456778";

        System.out.println(decode(compress(s)));
    }

}
