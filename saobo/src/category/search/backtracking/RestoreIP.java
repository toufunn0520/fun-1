package category.search.backtracking;

import java.util.ArrayList;
import java.util.List;

public class RestoreIP {

    private void backTrack(String current, int countOfDot, String ip, List<String> results) {
        if (countOfDot == 0) {
            if (isValidIp(current)) {
                results.add(ip + current);
            }
            return;
        }

        for (int i = 1; i < 4; i++) {
            if (i > current.length()) {
                break;
            }

            String curIp = current.substring(0, i);
            if (isValidIp(curIp)) {
                backTrack(current.substring(i), countOfDot - 1, ip + curIp + ".", results);
            }
        }

    }

    private boolean isValidIp(String string) {
        if (string.length() == 0 || (string.length() > 1 && string.startsWith("0"))) {
            return false;
        }

        int ipDigits = Integer.parseInt(string);
        return ipDigits < 256 && ipDigits >= 0;
    }

    /**
     * [Leetcode 93] https://leetcode.com/problems/restore-ip-addresses/
     * 
     * <pre>
     * Given a string containing only digits, restore it by returning all possible valid IP address combinations. For
     * example: Given "25525511135", return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
     * </pre>
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> results = new ArrayList<String>();

        if (s.length() > 12 || s.length() < 4) {
            return results;
        }

        backTrack(s, 3, new String(), results);

        return results;
    }
}
