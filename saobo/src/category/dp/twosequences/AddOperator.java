package category.dp.twosequences;

public class AddOperator {

    public static void main(String[] args) {
        int[] nums = { 1, 10, 1 };

        System.out.println(maxValueAfterAddingOperators(nums));
    }

    /**
     * [snap*chat] double array, 让输出能使用＊，＋， 以及（）得到的maximum
     *
     * @param array
     * @return
     */
    public static double maxValueAfterAddingOperators(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[][] maxValue = new int[nums.length][nums.length];

        for (int i = 0; i < nums.length; i++) {
            maxValue[i][i] = nums[i];
        }

        for (int endIndex = 1; endIndex < nums.length; endIndex++) {
            for (int startIndex = 0; startIndex < nums.length; startIndex++) {
                for (int splitIndex = startIndex; splitIndex < endIndex; splitIndex++) {
                    int left = maxValue[startIndex][splitIndex];
                    int right = maxValue[splitIndex + 1][endIndex];

                    int currentMax = Math.max(left * right, left + right);
                    maxValue[startIndex][endIndex] = Math.max(maxValue[startIndex][endIndex], currentMax);
                }
            }
        }

        return maxValue[0][nums.length - 1];
    }
}
