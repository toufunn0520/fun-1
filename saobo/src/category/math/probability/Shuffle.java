package category.math.probability;

import java.util.Random;

public class Shuffle {

    public static void main(String[] args) {
        int[] num = { 1, 2, 3, 4, 5 };

        for (int i = 0; i < 10; i++) {
            shuffileArray(num);
            for (int a : num) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }

    public static void shuffileArray(int[] num) {
        int len = num.length;
        Random random = new Random();

        for (int i = len - 1; i > 0; i--) {
            for (int j = i; j >= 0; j--) {
                int current = random.nextInt(i);
                swap(num, current, i);
            }
        }
    }

    private static void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
}
