package category.search.backtracking.permutationcombination;

import interview.utils.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    public static void main(String[] args) {

        int[] candidates = { 1, 2, 2, 4 };

        List<List<Integer>> result = new CombinationSum().combinationSum(candidates, 6);
        ListUtils.listsPrint(result);

    }

    /**
     * [Leetcode 39] https://leetcode.com/problems/combination-sum/
     *
     * <pre>
     * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be
     * used and each combination should be a unique set of numbers. Ensure that numbers within the set are sorted in
     * ascending order.
     *
     * Example 1:
     *
     * Input: k = 3, n = 7
     * Output: [[1,2,4]]
     *
     * Example 2:
     *
     * Input: k = 3, n = 9
     * Output: [[1,2,6], [1,3,5], [2,3,4]]
     *
     * </pre>
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();

        if (candidates == null || candidates.length == 0) {
            return results;
        }

        Arrays.sort(candidates);
        List<Integer> result = new ArrayList<Integer>();
        combinationSumHelper(candidates, target, 0, result, results);

        return results;
    }

    /**
     * [Leetcode 40] https://leetcode.com/problems/combination-sum-ii/
     *
     * <pre>
     * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
     *
     * Each number in C may only be used once in the combination.
     *
     * Note:
     * All numbers (including target) will be positive integers.
     * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
     * The solution set must not contain duplicate combinations.
     * For example, given candidate set 10,1,2,7,6,1,5 and target 8,
     * A solution set is:
     * [1, 7]
     * [1, 2, 5]
     * [2, 6]
     * [1, 1, 6]
     * </pre>
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();

        if (candidates == null || candidates.length == 0) {
            return results;
        }

        Arrays.sort(candidates);
        List<Integer> result = new ArrayList<Integer>();
        combinationSum2Helper(candidates, target, 0, result, results);

        return results;
    }

    private void combinationSum2Helper(int[] candidates, int target, int start, List<Integer> result,
            List<List<Integer>> results) {
        if (target == 0) {
            results.add(new ArrayList<Integer>(result));
            return;
        } else if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];
            result.add(candidate);
            combinationSumHelper(candidates, target - candidate, i + 1, result, results);
            result.remove(result.size() - 1);

            while (i < candidates.length - 1 && candidates[i] == candidates[i + 1])
                i++;
        }
    }

    /**
     * [Leetcode 216] https://leetcode.com/problems/combination-sum-iii/
     *
     * <pre>
     * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be
     * used and each combination should be a unique set of numbers. Ensure that numbers within the set are sorted in ascending
     * order.
     *
     * Example 1:
     *
     * Input: k = 3, n = 7
     *
     * Output: [[1,2,4]]
     *
     * Example 2:
     *
     * Input: k = 3, n = 9
     *
     * Output: [[1,2,6], [1,3,5], [2,3,4]]
     * </pre>
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (k <= 0 || n <= 0 || k > 9 || n > 45) {
            return result;
        }

        List<Integer> combination = new ArrayList<Integer>();

        combinationSum3Helper(n, k, 1, combination, result);

        return result;
    }

    private void combinationSum3Helper(int target, int k, int startNumber, List<Integer> combination,
            List<List<Integer>> result) {
        if (k == 1) {
            if (target >= startNumber && target <= 9) {
                combination.add(target);
                result.add(new ArrayList<Integer>(combination));
                combination.remove(combination.size() - 1);
            }
            return;
        }

        for (int i = startNumber; i <= 9; i++) {
            if (target - i > 0) {
                combination.add(i);
                combinationSum3Helper(target - i, k - 1, i + 1, combination, result);
                combination.remove(combination.size() - 1);
            } else {
                break;
            }
        }

    }

    private void combinationSumHelper(int[] candidates, int target, int start, List<Integer> result,
            List<List<Integer>> results) {
        if (target == 0) {
            results.add(new ArrayList<Integer>(result));
            return;
        } else if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];
            result.add(candidate);
            combinationSumHelper(candidates, target - candidate, i, result, results);
            result.remove(result.size() - 1);
        }
    }

}
