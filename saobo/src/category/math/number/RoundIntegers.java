package category.math.number;

import java.util.Arrays;

/*
 * To execute Java, please define "static void main" on a class named Solution. If you need more classes, simply define
 * them inline. Given an array of numbers A = [x1, x2, ..., xn] and T = Round(x1+x2+... +xn). We want to find a way to
 * round each element in A such that after rounding we get a new array B = [y1, y2, ...., yn] such that y1+y2+...+yn = T
 * where yi = Floor(xi) or Ceil(xi), ceiling or floor of xi. We also want to minimize sum |x_i-y_i|.
 */
public class RoundIntegers {

    public static void main(String[] args) {
        Integer one = 7;
        byte[] a = new byte[4];
        a[1] = 1;

        System.out.println(a.length);

        // RoundIntegers s = new RoundIntegers();
        // Double[][] inputs = { { 1.2, 1.9, 1.8 }, { 1.2, 1.3, 1.1 }, { 1.2, 1.2, 1.2 }, { 1.6, 1.6, 1.6 } };
        // for (Double[] in : inputs) {
        // for (int i : s.rounding(in)) {
        // System.out.print(i + " ");
        // }
        // System.out.println();
        // }
    }

    public int[] rounding(Double[] nums) {

        long floorSum = 0;
        double sum = 0;

        for (double num : nums) {
            floorSum += Math.floor(num);
            sum += num;
        }

        int numSwithToCeil = (int) (Math.round(sum) - floorSum);

        Arrays.sort(nums, (num1, num2) -> (Double.compare(Math.floor(num1) + 1 - num1, Math.floor(num2) + 1 - num2)));

        for (int i = 0; i < nums.length; i++) {
            if (i < numSwithToCeil) {

            }
        }

        return null;
    }
}
