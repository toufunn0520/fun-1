package category.search.backtracking.permutationcombination;

import java.util.ArrayList;

public class KSum2 {

    public static void main(String[] args) {
        int[] A = { 1, 2, 3 };

        System.out.println(new KSum2().kSum2(A, 2, 5));

    }

    /**
     * Given n unique integers, number k (1<=k<=n) and target. Find all possible k integers where their sum is target.
     * Example Given [1,2,3,4], k=2, target=5, [1,4] and [2,3] are possible solutions.
     * 
     * @param A
     * @param k
     * @param target
     * @return
     */
    public ArrayList<ArrayList<Integer>> kSum2(int[] A, int k, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        if (k <= 0 || A == null || target <= 0) {
            return result;
        }

        ArrayList<Integer> current = new ArrayList<Integer>();
        kSum2Helper(A, 0, k, target, current, result);
        return result;
    }

    private void kSum2Helper(int[] A, int start, int k, int target, ArrayList<Integer> current,
            ArrayList<ArrayList<Integer>> result) {
        if (k == current.size() && target == 0) {
            result.add(new ArrayList<Integer>(current));
            return;
        }

        for (int i = start; i < A.length; i++) {
            current.add(A[i]);
            kSum2Helper(A, i + 1, k, target - A[i], current, result);
            current.remove(current.size() - 1);
        }
    }

}
