package category.dp.sequence;

import java.util.Arrays;

public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    private static void processFirstTrade(int[] prices, int[] left) {
        int min = prices[0];
        left[0] = 0;
        int curMaxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - min > curMaxProfit) {
                curMaxProfit = prices[i] - min;
            } else if (prices[i] < min) {
                min = prices[i];
            }

            left[i] = curMaxProfit;
        }
    }

    private static void processSecondTrade(int[] prices, int[] right) {
        int lastIndex = prices.length - 1;

        int max = prices[lastIndex];
        right[lastIndex] = 0;
        int curMaxProfit = 0;
        for (int i = lastIndex - 1; i >= 0; i--) {
            if (max - prices[i] > curMaxProfit) {
                curMaxProfit = max - prices[i];
            } else if (prices[i] > max) {
                max = prices[i];
            }

            right[i] = curMaxProfit;
        }
    }

    /**
     * [Leetcode 121] https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
     *
     * <pre>
     * Say you have an array for which the ith element is the price of a given stock on day i. If you were only
     * permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm
     * to find the maximum profit.
     * </pre>
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;

        int maxProfit = 0;
        int preMin = prices[0];
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] - preMin > maxProfit) {
                maxProfit = prices[i] - preMin;
            } else if (prices[i] < preMin) {
                preMin = prices[i];
            }
        }

        return maxProfit;
    }

    /**
     * [Leetcode 122] https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
     *
     * <pre>
     * Say you have an array for which the ith element is the price of a given stock on day i. Design an algorithm to
     * find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the
     * stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell
     * the stock before you buy again).
     * </pre>
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int maxProfit = 0;

        int prevTrade = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prevTrade) {
                maxProfit += prices[i] - prevTrade;
            }

            prevTrade = prices[i];
        }
        return maxProfit;
    }

    /**
     * [Leetcode 123] https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
     *
     * <pre>
     * Say you have an array for which the ith element is the price of a given stock on day i. Design an algorithm to
     * find the maximum profit. You may complete at most two transactions. Note: You may not engage in multiple
     * transactions at the same time (ie, you must sell the stock before you buy again).
     * </pre>
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1)
            return 0;

        int maxProfits = 0;

        int[] left = new int[prices.length];
        int[] right = new int[prices.length];

        processFirstTrade(prices, left);
        processSecondTrade(prices, right);

        for (int i = 0; i < prices.length - 1; i++) {
            if (left[i] + right[i] > maxProfits) {
                maxProfits = left[i] + right[i];
            }
        }

        return maxProfits;
    }

    /**
     * [Leetcode 188] https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
     * 
     * <pre>
     * Say you have an array for which the ith element is the price of a given stock on day i. Design an algorithm to
     * find the maximum profit. You may complete at most k transactions. Note: You may not engage in multiple
     * transactions at the same time (ie, you must sell the stock before you buy again).
     * </pre>
     * 
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit4(int k, int[] prices) {
        int len = prices.length;
        if (len < 2)
            return 0;
        if (k > len / 2) { // simple case
            int ans = 0;
            for (int i = 1; i < len; i++) {
                ans += Math.max(prices[i] - prices[i - 1], 0);
            }
            return ans;
        }

        int[] hold = new int[k + 1];
        Arrays.fill(hold, -prices[0]);

        int[] release = new int[k + 1];
        Arrays.fill(release, 0);

        int cur;
        for (int i = 1; i < len; i++) {
            cur = prices[i];
            for (int j = k; j > 0; j--) {
                release[j] = Math.max(release[j], hold[j] + cur);
                hold[j] = Math.max(hold[j], release[j - 1] - cur);
            }
        }
        return release[k];
    }

}
