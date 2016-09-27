package category.implementation.string.parse;

import java.util.ArrayList;
import java.util.List;

public class ParseCSV {

    public static void main(String[] args) {
        // #1
        // List<String> output = parseCSV("John,Smith,john.smith@gmail.com,Los Angeles,1");
        // String strOutput = printStr(output);
        // System.out.println(strOutput);
        // // #2.
        // output = parseCSV("Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0");
        // strOutput = printStr(output);
        // System.out.println(strOutput);
        List<String> output = parseCSV("\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1'");
        // "Alexandra ""Alex""",Menendez,alex.menendez@gmail.com,Miami,1
        String strOutput = printStr(output);
        System.out.println(strOutput);
    }

    public static List<String> parseCSV(String str) {
        List<String> result = new ArrayList<String>();

        boolean inQuote = false;
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (inQuote) {
                if (str.charAt(i) == '"') {
                    if (i == str.length() - 1) {
                        result.add(buffer.toString());
                        return result;
                    } else if (str.charAt(i + 1) == '"') {
                        buffer.append('"');
                        i++;
                    } else {
                        result.add(buffer.toString());
                        buffer.setLength(0);
                        inQuote = false;
                        i++;
                    }
                } else {
                    buffer.append(str.charAt(i));
                }
            } else {
                if (str.charAt(i) == '"') {
                    inQuote = true;
                } else if (str.charAt(i) == ',') {
                    result.add(buffer.toString());
                    buffer.setLength(0);
                } else {
                    buffer.append(str.charAt(i));
                }

            }

        }
        if (buffer.length() > 0)
            result.add(buffer.toString());
        return result;
    }

    public static String printStr(List<String> list) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < list.size() - 1; i++) {
            res.append(list.get(i));
            res.append('|');
        }
        res.append(list.get(list.size() - 1));
        return res.toString();
    }
}
