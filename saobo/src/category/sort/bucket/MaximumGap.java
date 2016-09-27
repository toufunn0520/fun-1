package category.sort.bucket;

import java.util.Arrays;

public class MaximumGap {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    /**
     * [Leetcode 164] https://leetcode.com/problems/maximum-gap/
     * 
     * <pre>
     * Given an unsorted array, find the maximum difference between the
     * successive elements in its sorted form. Try to solve it in linear
     * time/space. Return 0 if the array contains less than 2 elements. You may
     * assume all elements in the array are non-negative integers and fit in the
     * 32-bit signed integer range.
     * </pre>
     * 
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = num < min ? num : min;
            max = num > max ? num : max;
        }

        if (max == min) {
            return 0;
        }

        int bucketLen = (max - min) / nums.length + 1;
        int numberOfBucket = (max - min) / bucketLen + 1;

        int[] upper = new int[numberOfBucket];
        int[] lower = new int[numberOfBucket];
        Arrays.fill(upper, -1);
        Arrays.fill(lower, -1);

        for (int num : nums) {
            int index = (num - min) / bucketLen;
            if (upper[index] == -1) {
                upper[index] = num;
            } else {
                upper[index] = num > upper[index] ? num : upper[index];
            }

            if (lower[index] == -1) {
                lower[index] = num;
            } else {
                lower[index] = num < lower[index] ? num : lower[index];
            }
        }

        int maxGap = 0;
        int prevUpper = -1;
        for (int i = 0; i < numberOfBucket; i++) {
            if (upper[i] == -1) {
                continue;
            } else {
                if (prevUpper != -1) {
                    maxGap = Math.max(lower[i] - prevUpper, maxGap);
                }
                prevUpper = upper[i];
            }
        }

        return maxGap;
    }

}
