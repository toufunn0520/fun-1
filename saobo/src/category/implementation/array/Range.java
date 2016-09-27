package category.implementation.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class Range {

    public static void main(String[] args) {
        int[] nums = { 1 };

        System.out.println(new Range().summaryRanges(nums));
    }

    /**
     * [Leetcode 163] https://leetcode.com/problems/missing-ranges/
     *
     * <pre>
     * Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.
     * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
     * </pre>
     *
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<String>();

        if (nums == null || nums.length == 0) {
            result.add(getRange(lower, upper));
            return result;
        }

        int prev = lower - 1;
        for (int i = 0; i <= nums.length; i++) {
            int cur = (i == nums.length) ? upper + 1 : nums[i];
            if (cur - prev > 1) {
                result.add(getRange(prev + 1, cur - 1));
            }
            prev = cur;
        }

        return result;
    }

    private String getRange(int start, int end) {
        return (start == end) ? String.valueOf(start) : start + "->" + end;
    }

    /**
     * [Leetcode 228] https://leetcode.com/problems/summary-ranges/
     * 
     * <pre>
     * Given a sorted integer array without duplicates, return the summary of its ranges. For example, given
     * [0,1,2,4,5,7], return ["0->2","4->5","7"].
     * </pre>
     * 
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> ranges = new ArrayList<String>();

        if (nums == null || nums.length == 0) {
            return ranges;
        }

        if (nums.length == 1) {
            ranges.add(String.valueOf(nums[0]));
            return ranges;
        }

        nums = Arrays.copyOf(nums, nums.length + 1);
        nums[nums.length - 1] = nums[nums.length - 2] - 1;

        int prev = nums[0];
        String currentString = String.valueOf(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (prev + 1 != nums[i]) {
                String prevString = String.valueOf(prev);
                if (!currentString.equals(prevString)) {
                    currentString += "->" + String.valueOf(prev);
                }

                ranges.add(new String(currentString));
                currentString = String.valueOf(nums[i]);
            }
            prev = nums[i];
        }

        return ranges;
    }

    @Test
    public void testWhenInputIsEmpty() {
        int[] nums = {};
        Assert.assertEquals(new ArrayList<String>(), new Range().summaryRanges(nums));
    }

    @Test
    public void testWhenInputIsMixed() {
        int[] nums = { 1, 2, 3, 5, 8, 9, 12, 13, 15 };
        String[] strings = { "1->3", "5", "8->9", "12->13", "15" };
        List<String> expected = Arrays.asList(strings);

        Assert.assertEquals(expected, new Range().summaryRanges(nums));
    }

    @Test
    public void testWhenInputIsMultipleNumbers() {
        int[] nums = { 1, 3, 5, 7, 9 };
        String[] strings = { "1", "3", "5", "7", "9" };
        List<String> expected = Arrays.asList(strings);

        Assert.assertEquals(expected, new Range().summaryRanges(nums));
    }

    @Test
    public void testWhenInputIsMultipleRanges() {
        int[] nums = { 1, 2, 3, 5, 6, 7, 8, 9, 12, 13 };
        String[] strings = { "1->3", "5->9", "12->13" };
        List<String> expected = Arrays.asList(strings);

        Assert.assertEquals(expected, new Range().summaryRanges(nums));
    }

    @Test
    public void testWhenInputIsNull() {
        Assert.assertEquals(new ArrayList<String>(), new Range().summaryRanges(null));
    }

    @Test
    public void testWhenInputIsOneNumber() {
        int[] nums = { 1 };
        List<String> expected = Arrays.asList("1");

        Assert.assertEquals(expected, new Range().summaryRanges(nums));
    }

    @Test
    public void testWhenInputIsOneRange() {
        int[] nums = { -1, 0, 1, 2, 3 };
        List<String> expected = Arrays.asList("-1->3");

        Assert.assertEquals(expected, new Range().summaryRanges(nums));
    }

}
