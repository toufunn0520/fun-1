package category.dp.cache;

import interview.utils.ListUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PerfectSquare {

    /**
     * Try to get a list of squares whose sum is the given number. The size of the list should be minimal.
     * 
     * @param number
     * @return
     */
    private static List<Integer> getMinSquere(int number) {
        List<Integer> result = new ArrayList<Integer>();

        Set<Integer> squares = new HashSet<Integer>();
        Map<Integer, Integer> sumOfTwoSquares2Square = new HashMap<Integer, Integer>();

        for (int i = 1; i <= number; i++) {
            // If implemented by Newton's method, the time complexcity of sqrt(int x) shall be
            // O(log(n)) with n-digit precision.
            int root = (int) Math.sqrt(i);
            int remaining = i - root * root;

            if (remaining == 0) {
                squares.add(i);
                continue;
            }

            for (int square : squares) {
                if (square <= i / 2 && squares.contains(i - square))
                    sumOfTwoSquares2Square.put(i, square);
            }
        }

        if (squares.contains(number)) {
            result.add(number);
            return result;
        }

        if (sumOfTwoSquares2Square.containsKey(number)) {
            result.add(number - sumOfTwoSquares2Square.get(number));
            result.add(sumOfTwoSquares2Square.get(number));
            return result;
        }

        for (int j = 2; j <= number / 2; j++) {
            if (sumOfTwoSquares2Square.containsKey(j) && squares.contains(number - j)) {
                result.add(number - j);
                result.add(j - sumOfTwoSquares2Square.get(j));
                result.add(sumOfTwoSquares2Square.get(j));
                return result;
            }
        }

        for (int j = 2; j <= number / 2; j++) {
            if (sumOfTwoSquares2Square.containsKey(j) && sumOfTwoSquares2Square.containsKey(number - j)) {
                result.add(number - j - sumOfTwoSquares2Square.get(number - j));
                result.add(sumOfTwoSquares2Square.get(number - j));
                result.add(j - sumOfTwoSquares2Square.get(j));
                result.add(sumOfTwoSquares2Square.get(j));
                return result;
            }
        }

        /*
         * According to Lagrange's four-square theorem, every natural number can be represented as the sum of four
         * integer squares, so the logic shall never come to any line below.
         */
        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = getMinSquere(100);

        ListUtils.listPrint(result);
    }

}
