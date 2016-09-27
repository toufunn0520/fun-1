package category.dp.twosequences;

import java.util.Arrays;

public class CatchThreft {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    /**
     * <pre>
     * 找小偷问题，有n个房间，其中一个房间有小偷。早上我们
     * 可以打开一个房间的门看小偷在不在里面，晚上小偷会向左边或者右边的房间走。
     * 现在给你一个开门的sequence，你输出这个sequence能不能保证找到小偷。
     * 比如：如果只有三个房间那么如果打开房间的sequence是{1，1}那么一定会找到小偷。
     * 因为如果小偷在中间那么第一天就会被找到，如果小偷在两边那么第二天一定回来
     * 到中间也会被找到。房间数为n，sequence长度为k
     * 
     * 
     * nextDay[k][n] : 第k天，第n个房间小偷是否可以survive 
     * nextDay[i][j] = (nextDay[i-1][j-1] or nextDay[i-1][j+1]) && strategy[i] !=j
     * </pre>
     *
     * @param n
     * @param strategy
     * @return
     */
    boolean canCatchTheft(int n, int strategy[]) {
        int k = strategy.length;
        // nextDay[i] means theft can survive in spot i or not on this day
        boolean nextDay[] = new boolean[n];
        boolean canSurvive, pre, a, b;
        // init the first day
        Arrays.fill(nextDay, true);
        nextDay[strategy[0]] = false;
        for (int i = 1; i < k; ++i) {
            canSurvive = false;
            pre = false;
            for (int j = 0; j < n; ++j) {
                a = j == 0 ? false : pre;
                b = j == n - 1 ? false : nextDay[j + 1];
                pre = nextDay[j]; // store current day for the next round
                nextDay[j] = ((a || b) && strategy[i] != j) ? true : false;
                if (nextDay[j] == true)
                    canSurvive = true;
            }
            if (!canSurvive)
                return true;
        }
        return false;
    }

}
