package category.dp.sequence;

import java.util.Arrays;
import java.util.Random;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {

        System.out.println("Generating a random array...");
        LongestIncreasingSubsequence lisUpdate = new LongestIncreasingSubsequence();
        int[] oldArray = new int[7];
        oldArray = lisUpdate.randomArray();
        System.out.println(Arrays.toString(oldArray)); // 输出生成的随机数组
        System.out.println("LIS length is:" + lisUpdate.getLIS(oldArray)); // 输出最长递增子序列的长度
    }

    /**
     * Time complexity : O(NlgN)
     * 
     * @param nums
     * @return
     */
    public int getLIS(int[] nums) {
        int i = 0, len = 1;
        int[] end = new int[nums.length + 1];
        end[1] = nums[0];

        for (i = 1; i < nums.length; i++) {
            int index = upper_bound(end, 1, len, nums[i]);
            end[index] = nums[i];
            if (len < index) {
                len = index;
            }
        }
        return len;
    }

    public int[] randomArray() { // 生成一个10以内的数组，长度为10
        Random random = new Random();
        int[] randomArray = new int[10];
        for (int i = 0; i < 10; i++) {
            randomArray[i] = random.nextInt(10);
        }

        return randomArray;
    }

    /**
     * //在非递减序列 nums[start..end]（闭区间）上二分查找第一个大于等于key的位置，如果都小于key，就返回end+1
     *
     * @param nums
     * @param start
     * @param end
     * @param key
     * @return
     */
    private int upper_bound(int[] nums, int start, int end, int key) {
        if (nums[end] <= key) {
            return end + 1;
        }

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= key) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }
}
