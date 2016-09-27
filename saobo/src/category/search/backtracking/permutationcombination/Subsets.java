package category.search.backtracking.permutationcombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {

    /**
     * [Leetcode 78] https://leetcode.com/problems/subsets/
     *
     * <pre>
     * Given a set of distinct integers, nums, return all possible subsets.
     *
     * Note:
     * Elements in a subset must be in non-descending order. The solution set must not contain duplicate subsets.
     *
     * For example, If nums = [1,2,3], a solution is:
     *
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     * </pre>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();

        if (nums == null || nums.length == 0) {
            return results;
        }

        List<Integer> result = new ArrayList<Integer>();
        Arrays.sort(nums);
        subsetsHelper(nums, result, 0, results);

        return results;
    }

    private void subsetsHelper(int[] nums, boolean[] visited, int start, List<Integer> result,
            List<List<Integer>> results) {
        results.add(new ArrayList<Integer>(result));

        for (int i = start; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }

            result.add(nums[i]);
            visited[i] = true;
            subsetsHelper(nums, visited, i + 1, result, results);
            result.remove(result.size() - 1);
            visited[i] = false;
        }
    }

    private void subsetsHelper(int[] nums, List<Integer> result, int start, List<List<Integer>> results) {
        results.add(new ArrayList<Integer>(result));
        for (int i = start; i < nums.length; i++) {
            result.add(nums[i]);
            subsetsHelper(nums, result, i + 1, results);
            result.remove(result.size() - 1);
        }
    }

    /**
     * [Leetcode 90] https://leetcode.com/problems/subsets-ii/
     * 
     * <pre>
     * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
     *
     * Note:
     * Elements in a subset must be in non-descending order.
     * The solution set must not contain duplicate subsets.
     * For example,
     * If nums = [1,2,2], a solution is:
     *
     * [
     *   [2],
     *   [1],
     *   [1,2,2],
     *   [2,2],
     *   [1,2],
     *   []
     * ]
     * </pre>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();

        if (nums == null || nums.length == 0) {
            return results;
        }

        List<Integer> result = new ArrayList<Integer>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        subsetsHelper(nums, visited, 0, result, results);

        return results;
    }
}
