package category.math.number;

import java.util.HashMap;
import java.util.Map;

public class AmicableNumber {

    private static int[][] getMatrix(int limit) {
        int[] array = new int[limit];
        for (int i = 2; i < limit; i++)
            array[i] = sumFactors(i);

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 2; i < limit; i++) {
            int j = array[i];
            if (j < i && i == array[j])
                map.put(i, j);
            // Check 'j < i' in order to:
            // 1. Avoid an illegal index when 'j >= limit'
            // 2. Avoid the insertion of the equivalent pair [j,i]
            // 3. Avoid the insertion of perfect numbers such as [6,6]
        }

        int[][] pairs = new int[map.size()][2];
        int index = 0;
        for (int key : map.keySet()) {
            pairs[index][0] = key;
            pairs[index][1] = map.get(key);
            index++;
        }
        return pairs;
    }

    public static void main(String[] args) {
        int[][] matrix = getMatrix(10000);
        for (int i = 0; i < matrix.length; i++)
            System.out.println(matrix[i][0] + " " + matrix[i][1]);
    }

    private static int sumFactors(int n) {
        int sum = 0;
        for (int div = 1; div <= n / 2; div++) {
            if (n % div == 0) {
                sum += div;
            }
        }
        return sum;
    }

    public boolean isAmicableNumber(int num1, int num2) {
        if (num1 <= 0 || num2 <= 0) {
            return false;
        }

        int sum1 = 0, sum2 = 0, i = 0;
        for (i = 0; i <= num1 / 2; i++) {
            if (num1 % i == 0) {
                sum1 += i;
            }
        }

        for (i = 0; i <= num2 / 2; i++) {
            if (num2 % i == 0) {
                sum2 += i;
            }
        }

        if (sum1 == num2 && sum2 == num1) {
            return true;
        } else {
            return false;
        }
    }
}
