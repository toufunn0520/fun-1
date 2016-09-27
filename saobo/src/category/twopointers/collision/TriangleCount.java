package category.twopointers.collision;

import java.util.Arrays;

public class TriangleCount {

    /**
     * Given an unsorted array of positive integers. Find the number of triangles that can be formed with three
     * different array elements as three sides of triangles. Time Complexity: O(n^2).
     *
     * @param numbers
     * @return
     */
    public static int getNumberOfTriangles(int[] numbers) {
        if (numbers == null || numbers.length < 3) {
            return 0;
        }

        int count = 0;
        int n = numbers.length;

        Arrays.sort(numbers);

        for (int i = 0; i < n - 2; i++) {
            int k = i + 2;

            for (int j = i + 1; j < n; j++) {
                while (k < n && numbers[i] + numbers[j] > numbers[k]) {
                    k++;
                }

                count += k - j - 1;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
