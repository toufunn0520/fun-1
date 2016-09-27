package category.dp.backpack;

/**
 * Max Sum Plus Plus Given a consecutive number sequence S1, S2, S3, S4 ... Sx, ... Sn (1 ≤ x ≤ n ≤ 1,000,000, -32768 ≤
 * Sx ≤ 32767). We define a function sum(i, j) = Si + ... + Sj (1 ≤ i ≤ j ≤ n). Now given an integer m (m > 0), your
 * task is to find m pairs of i and j which make sum(i1, j1) + sum(i2, j2) + sum(i3, j3) + ... + sum(im, jm) maximal (ix
 * ≤ iy ≤ jx or ix ≤ jy ≤ jx is not allowed). Basic idea: curMax[i][j]=max(curMax[i][j-1]+num[j],curMax(i-1,t)+num[j])
 * i-1<=t<=j-1. curMax[i][j] means ending with number[j] and we have i parts of sub array. since Max(curMax(i-1, t)) i-1
 * <=t<= j-1 is the previous max. We can simpily this to curMax[i] = max(curMax[i-1], preMax(i-1)) + num[i]
 * 
 * @author boyi
 */
public class MaxSumPlusPlus {

    public static void main(String[] args) {
        int[] numbers = { -1, 4, -2, 3, -2, 3 };
        System.out.println(getMaxSum(numbers, 2));

    }

    public static int getMaxSum(int[] numbers, int numberOfSub) {

        int length = numbers.length;
        int[] currentMax = new int[length];
        int[] previousMax = new int[length];
        int maxSum = Integer.MIN_VALUE;

        int j = 0;
        for (int i = 0; i < numberOfSub; i++) {
            for (j = i + 1; j < length; j++) {
                currentMax[j] = Math.max(currentMax[j - 1], previousMax[j - 1]) + numbers[j];
                previousMax[j - 1] = maxSum;
                maxSum = Math.max(maxSum, currentMax[j]);
            }

            previousMax[j - 1] = maxSum;
        }

        return maxSum;
    }
}
