package category.greedy;

import java.util.Arrays;

public class Candy {

    public static void main(String[] args) {
        int[] ratings = { 1, 5, 3, 2 };

        System.out.println(new Candy().candy(ratings));
    }

    /**
     * [Leetcode 135] https://leetcode.com/problems/candy/
     * 
     * <pre>
     * There are N children standing in a line. Each child is assigned a rating value.
     * You are giving candies to these children subjected to the following requirements:
     *
     * Each child must have at least one candy.
     * Children with a higher rating get more candies than their neighbors.
     * What is the minimum candies you must give?
     * </pre>
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        if (ratings.length == 0) {
            return 0;
        }

        if (ratings.length == 1) {
            return 1;
        }

        int[] candy = new int[ratings.length];
        int len = ratings.length;

        Arrays.fill(candy, 1);

        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1] && candy[i] <= candy[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            }
        }

        for (int i = len - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i] && candy[i - 1] <= candy[i]) {
                candy[i - 1] = candy[i] + 1;
            }
        }

        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += candy[i];
        }

        return sum;
    }
}
