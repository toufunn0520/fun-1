package category.graph.directed;

public class MaxLoop {

    public static void main(String[] args) {
        int[][] nums = { { 1, 2, 3, 4, 0 }, { 3, 5, 1, 6, 2, 4, 0 }, { 2, 2, 2, 2 }, { 3, 2, 1, 4, 0 },
                { 1, 2, 3, 4, 2, 0 } };

        for (int i = 0; i < nums.length; i++) {
            System.out.println(new MaxLoop().getMaxLoopLength(nums[i]));
        }
    }

    private int dfs(int index, int length, int[] lengths, int[] nums, boolean[] visited) {
        if (visited[index]) {
            return length - lengths[index];
        }

        visited[index] = true;
        lengths[index] = length;
        return dfs(nums[index], length + 1, lengths, nums, visited);
    }

    /**
     * <pre>
     * Given an array
     * Indexes 0 1 2 3 4
     * Values 3 2 1 4 0
     * Values are the next index of the jump 0 -> 3 -> 4 -> 0... 1 -> 2 -> 1...
     * Write a function to detect if there are loops. If yes, get the max length of the loop possible, otherwise just return zero.
     * </pre>
     *
     * @param nums
     * @return
     */
    public int getMaxLoopLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        boolean[] visited = new boolean[nums.length];
        int[] lengths = new int[nums.length];

        int maxLoopLength = 0;
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            int currentLoopLength = dfs(i, 0, lengths, nums, visited);
            maxLoopLength = Math.max(maxLoopLength, currentLoopLength);
        }

        return maxLoopLength;
    }
}
