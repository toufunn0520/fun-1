package category.twopointers.collision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sum {

    public static void main(String[] args) {
        int[] nums = { 1, 0, -1, 0, -2, 2 };

        List<List<Integer>> lists = new Sum().fourSum(nums, 0);
        for (List<Integer> list : lists) {
            for (Integer i : list) {
                System.out.print(i);
                System.out.print(" ");
            }

            System.out.println();
        }

    }

    /**
     * [Leetcode 18] https://leetcode.com/problems/4sum/
     *
     * <pre>
     * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
     *
     * Note:
     * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
     * The solution set must not contain duplicate quadruplets.
     *     For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
     *
     *     A solution set is:
     *     (-1,  0, 0, 1)
     *     (-2, -1, 1, 2)
     *     (-2,  0, 0, 2)
     * </pre>
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();

        if (nums == null || nums.length < 4) {
            return results;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int left = j + 1;
                int right = nums.length - 1;

                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        List<Integer> result = new ArrayList<Integer>();
                        result.add(nums[i]);
                        result.add(nums[j]);
                        result.add(nums[left]);
                        result.add(nums[right]);
                        results.add(result);
                        right--;
                        left++;
                        while (nums[left] == nums[left - 1] && left < nums.length - 1) {
                            left++;
                        }
                    } else if (sum > target) {
                        right--;
                    } else {
                        left++;
                    }

                }
            }

        }

        return results;
    }

    /**
     * [Leetcode 15] https://leetcode.com/problems/3sum/
     *
     * <pre>
     * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
     *
     * Note:
     * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
     * The solution set must not contain duplicate triplets.
     *     For example, given array S = {-1 0 1 2 -1 -4},
     *
     *     A solution set is:
     *     (-1, 0, 1)
     *     (-1, -1, 2)
     * </pre>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> threeSumList = new ArrayList<List<Integer>>();

        for (int i = 0; i < nums.length; i++) {
            int twoSum = 0 - nums[i];
            if (i == 0 || nums[i] > nums[i - 1]) {
                int j = i + 1;
                int k = nums.length - 1;
                while (j < k) {
                    if (nums[j] + nums[k] == twoSum) {
                        while (j + 1 < nums.length && nums[j] == nums[j + 1]) {
                            j++;
                        }

                        while (k - 1 >= 0 && nums[k] == nums[k - 1]) {
                            k--;
                        }

                        List<Integer> threeSumInts = new ArrayList<Integer>();
                        threeSumInts.add(nums[i]);
                        threeSumInts.add(nums[j]);
                        threeSumInts.add(nums[k]);
                        threeSumList.add(threeSumInts);

                        j++;

                    } else if (nums[j] + nums[k] > twoSum) {
                        k--;
                    } else {
                        j++;
                    }
                }
            }
        }
        return threeSumList;
    }

    /**
     * [Leetcode 16] https://leetcode.com/problems/3sum-closest/
     *
     * <pre>
     * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
     *
     *     For example, given array S = {-1 2 1 -4}, and target = 1.
     *
     *     The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     * </pre>
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minGap = Integer.MAX_VALUE;

        int sum = 0;
        if (nums.length <= 2) {
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            return sum;
        }

        for (int i = 0; i < nums.length - 2; i++) {
            int current = nums[i];
            int startIndex = i + 1;
            int endIndex = nums.length - 1;
            while (startIndex < endIndex) {
                sum = current + nums[startIndex] + nums[endIndex];
                int difference = target - sum;
                if (Math.abs(difference) < Math.abs(minGap)) {
                    minGap = difference;
                }

                if (difference > 0) {
                    startIndex++;
                } else {
                    endIndex--;
                }
            }
        }

        return target - minGap;
    }

    /**
     * [Leetcode 259] https://leetcode.com/problems/3sum-smaller/
     * 
     * <pre>
     * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n 
     * that satisfy the condition nums[i] + nums[j] + nums[k] < target.
     * 
     * For example, given nums = [-2, 0, 1, 3], and target = 2.
     * Return 2. Because there are two triplets which sums are less than 2:
     * 
     * [-2, 0, 1]
     * [-2, 0, 3]
     * </pre>
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }

        Arrays.sort(nums);

        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] >= target) {
                    k--;
                } else {
                    count += k - j;
                    j++;
                }
            }
        }

        return count;
    }

    /**
     * [Leetcode 1] https://leetcode.com/problems/two-sum/
     *
     * <pre>
     * Given an array of integers, find two numbers such that they
     * add up to a specific target number. The function twoSum should return indices of the two numbers such that they
     * add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1
     * and index2) are not zero-based. You may assume that each input would have exactly one solution.
     *
     * Input: numbers={2, 7, 11, 15}, target=9
     * Output: index1=1, index2=2
     * </pre>
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        int[] indexes = new int[2];

        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            if (hashMap.containsKey(num)) {
                indexes[0] = hashMap.get(num) + 1;
                indexes[1] = i + 1;
            } else {
                hashMap.put(nums[i], i);
            }
        }

        return indexes;
    }
}
