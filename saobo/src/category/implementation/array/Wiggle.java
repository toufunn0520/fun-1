package category.implementation.array;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Test;

public class Wiggle {

    public static void main(String[] args) {
        // int[] nums = { 1, 2, 5, 2, 8, 12, 15, 16, 20, 18 };
        // new Wiggle().sort(nums);
        //
        // for (int i = 0; i < nums.length; i++) {
        // System.out.print(nums[i] + " ");
        // }

        System.out.println();

        Integer[] numbers = { 1, 2, 5, 3, 8, 12, 15, 16, 20, 18 };

        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }

        // char[] trend = new String("IDDDIIDDI").toCharArray();
        //
        // for (int i = 0; i < numbers.length; i++) {
        // System.out.print(numbers[i] + " ");
        // }
        // System.out.println();
        // new Wiggle().sort(trend, numbers);
        //
        // for (int i = 0; i < numbers.length; i++) {
        // System.out.print(numbers[i] + " ");
        // }
    }

    private void assertTrendConsistent(char[] trend, Integer[] nums) {
        for (int i = 0; i < trend.length - 1 && i < nums.length - 1; i++) {
            if (trend[i] == 'I') {
                Assert.assertTrue(nums[i] < nums[i + 1]);
            } else if (trend[i] == 'D') {
                Assert.assertTrue(nums[i] > nums[i + 1]);
            }
        }
    }

    /**
     * Follow up: what if given a trend char array indicates the trend is increasing or decreasing by giving 'I' or 'D'.
     * Sort the array so that it will be consistent with the trending array.
     *
     * @param trend
     * @param nums
     */
    public void sort(char[] trend, Integer[] nums) {
        if (trend == null || nums == null || nums.length == 0 || trend.length == 0) {
            return;
        }

        boolean isIncreasingFlag = trend[0] == 'I' ? true : false;
        char curTrend = trend[0];
        int startIndex = 0;
        int i = 1;
        while (i < trend.length && i < nums.length) {
            int endIndex = i;
            while (i < trend.length - 1 && i < nums.length - 1 && trend[i] == curTrend) {
                i++;
            }
            endIndex = i;
            if (isIncreasingFlag) {
                sortInclusiveRange(nums, startIndex, endIndex, (num1, num2) -> Integer.compare(num1, num2));
            } else {
                sortInclusiveRange(nums, startIndex, endIndex, (num1, num2) -> Integer.compare(num2, num1));
            }
            startIndex = i;
            isIncreasingFlag = !isIncreasingFlag;

            if (i < trend.length) {
                curTrend = trend[i];
                i++;
            }
        }
    }

    /**
     * Sort the array so that a1 <= a2 >= a3 <= a4 >=...
     *
     * @param nums
     */
    public void sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        boolean isIncreasingFlag = true;
        for (int i = 1; i < nums.length; i++) {
            if (isIncreasingFlag ^ nums[i] > nums[i - 1]) {
                int temp = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = temp;
            }
            isIncreasingFlag = !isIncreasingFlag;
        }
    }

    private void sortInclusiveRange(Integer[] nums, Integer startIndex, Integer endIndex, Comparator<Integer> comparator) {
        Integer[] sortPart = new Integer[endIndex - startIndex + 1];
        for (int i = 0; i < endIndex - startIndex + 1; i++) {
            sortPart[i] = nums[startIndex + i];
        }

        Arrays.sort(sortPart, comparator);

        for (int i = 0; i < endIndex - startIndex + 1; i++) {
            nums[startIndex + i] = sortPart[i];
        }
    }

    @Test
    public void testWithAllDecreasing() {
        char[] trend = new String("DDDDDDDDDD").toCharArray();
        Integer[] nums = { 2, 3, 1, 5, 23, 12, 6, 8, 32, 19 };
        new Wiggle().sort(trend, nums);

        Integer[] expected = { 32, 23, 19, 12, 8, 6, 5, 3, 2, 1 };
        Assert.assertArrayEquals(expected, nums);
    }

    @Test
    public void testWithAllIncreasing() {
        char[] trend = new String("IIIIIIIIII").toCharArray();
        Integer[] nums = { 2, 3, 1, 5, 23, 12, 6, 8, 32, 19 };
        new Wiggle().sort(trend, nums);

        Integer[] expected = { 1, 2, 3, 5, 6, 8, 12, 19, 23, 32 };
        Assert.assertArrayEquals(expected, nums);
    }

    @Test
    public void testWithWiggle() {
        char[] trend = new String("IDIDIDIDID").toCharArray();
        Integer[] nums = { 2, 3, 1, 5, 23, 12, 6, 8, 32, 19 };
        new Wiggle().sort(trend, nums);

        Integer[] expected = { 2, 3, 1, 23, 5, 12, 6, 32, 8, 19 };
        Assert.assertArrayEquals(expected, nums);
    }

    @Test
    public void testWithWiggle2() {
        char[] trend = new String("DIDIDIDIDI").toCharArray();
        Integer[] nums = { 2, 3, 1, 5, 23, 12, 6, 8, 32, 19 };
        new Wiggle().sort(trend, nums);

        assertTrendConsistent(trend, nums);
    }

    @Test
    public void testWithWiggleWithTwo() {
        char[] trend = new String("DDIIDDIIDD").toCharArray();
        Integer[] nums = { 2, 3, 1, 5, 23, 12, 6, 8, 32, 19 };
        new Wiggle().sort(trend, nums);

        assertTrendConsistent(trend, nums);
    }

}
