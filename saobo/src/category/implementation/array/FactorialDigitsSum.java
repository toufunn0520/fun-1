package category.implementation.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Linkedin: For example: 10! = 3628800, then output should be 3+6+2+8+8+0+0 = 27; The time complexity is high in the
 * solution, but it handles integer overflow much better.
 */
public class FactorialDigitsSum {

    public static void main(String[] args) {
        System.out.println(new FactorialDigitsSum().factorialDigitsSum(10));
    }

    public int factorialDigitsSum(int n) {
        if (n < 0)
            throw new RuntimeException("negative number caculation is not supported.");

        List<Integer[]> integersInArray = generateIntegersInArray(n);

        Integer[] factorialResult = getFactorialResult(integersInArray);

        return getDigitsSum(factorialResult);
    }

    private int getDigitsSum(Integer[] factorialResult) {
        int sum = 0;
        for (Integer integer : factorialResult) {
            sum += integer;
        }
        return sum;
    }

    private Integer[] getFactorialResult(List<Integer[]> integersInArray) {
        Integer[] factorialResult = { 1 };
        for (Integer[] current : integersInArray) {
            factorialResult = multiply(factorialResult, current);
        }
        return factorialResult;
    }

    public List<Integer[]> generateIntegersInArray(int n) {
        List<Integer[]> integersInArray = new ArrayList<Integer[]>(n);

        for (int i = 2; i < n; i++) {
            Integer[] integerInArray = intToArray(i);
            integersInArray.add(integerInArray);
        }

        return integersInArray;
    }

    private Integer[] intToArray(int n) {
        int size = 0;
        int copy = n;
        while (copy != 0) {
            size++;
            copy /= 10;
        }
        Integer[] arr = new Integer[size];
        copy = n;
        for (int i = 0; i < size; i++) {
            arr[i] = copy % 10;
            copy /= 10;
        }
        return arr;
    }

    private Integer[] multiply(Integer[] pre, Integer[] curr) {
        Integer[] result = new Integer[pre.length + curr.length];
        Arrays.fill(result, 0);
        int carry = 0;
        for (int i = 0; i < pre.length; i++) {
            for (int j = 0; j < curr.length; j++) {
                result[i + j] += pre[i] * curr[j] + carry;
                carry = result[i + j] / 10;
                result[i + j] %= 10;
            }
            if (carry != 0) {
                result[i + curr.length] += carry;
                carry = 0;
            }
        }
        return result;
    }
}
