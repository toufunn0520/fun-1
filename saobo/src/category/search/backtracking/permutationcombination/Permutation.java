package category.search.backtracking.permutationcombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutation {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 2 };

        List<List<Integer>> results = new Permutation().permuteUnique(nums);

        // ListUtils.listsPrint(results);

        System.out.println(new Permutation().getPermutation(3, 2));
    }

    /**
     * [Leetcode 60] https://leetcode.com/problems/permutation-sequence/
     * 
     * <pre>
     * The set [1,2,3,…,n] contains a total of n! unique permutations. By listing and labeling all of the permutations
     * in order, We get the following sequence (ie, for n = 3): "123" "132" "213" "231" "312" "321" Given n and k,
     * return the kth permutation sequence. Note: Given n will be between 1 and 9 inclusive.
     * </pre>
     * 
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        if (n <= 0) {
            return "";
        }

        int sum = 1;
        List<Integer> digits = new ArrayList<Integer>();
        for (int i = 1; i < n; i++) {
            digits.add(i);
            sum *= i;
        }

        digits.add(n);
        k--;

        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int i = 1; i <= n; i++) {
            index = k / sum;
            sb.append(digits.get(index));
            digits.remove(index);

            if (i == n) {
                break;
            }

            k %= sum;
            sum /= (n - i);
        }

        return sb.toString();
    }

    /**
     * [Leetcode 46] https://leetcode.com/problems/permutations/
     *
     * <pre>
     * Given a collection of numbers, return all possible permutations. For example, [1,2,3] have the following
     * permutations: [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
     * </pre>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        List<Integer> result = new ArrayList<Integer>();

        if (nums == null) {
            return results;
        }

        Set<Integer> visited = new HashSet<Integer>();
        permuteHelper(result, nums, visited, results);
        return results;
    }

    public void permuteHelper(List<Integer> result, int[] nums, Set<Integer> visited, List<List<Integer>> results) {
        if (result.size() == nums.length) {
            results.add(new ArrayList<Integer>(result));
        }

        for (int num : nums) {
            if (visited.contains(num)) {
                continue;
            }
            result.add(num);
            visited.add(num);
            permuteHelper(result, nums, visited, results);
            result.remove(result.size() - 1);
            visited.remove(num);
        }
    }

    /**
     * [Leetcode 47] https://leetcode.com/problems/permutations-ii/
     *
     * <pre>
     * Given a collection of numbers that might contain duplicates, return all possible unique permutations. For
     * example, [1,1,2] have the following unique permutations: [1,1,2], [1,2,1], and [2,1,1].
     * </pre>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        List<Integer> result = new ArrayList<Integer>();

        if (nums == null) {
            return results;
        }

        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        permuteUniqueHelper(result, nums, visited, results);
        return results;
    }

    public void permuteUniqueHelper(List<Integer> result, int[] nums, boolean[] used, List<List<Integer>> results) {
        if (result.size() == nums.length) {
            results.add(new ArrayList<Integer>(result));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if ((i > 0 && !used[i - 1] && nums[i - 1] == nums[i]) || used[i]) {
                continue;
            }
            result.add(nums[i]);
            used[i] = true;
            permuteUniqueHelper(result, nums, used, results);
            result.remove(result.size() - 1);
            used[i] = false;
        }
    }
}
