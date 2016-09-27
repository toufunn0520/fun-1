package category.math.number;

public class NumbersInFamily {

    /**
     * Two non-negative integers are called siblings if they can be obtained from each other by rearranging the digits
     * of their decimal representations. Given a non-negative integer num, returns the largest number in the family of
     * num. Return -1 if the result exceeds 100000000.
     *
     * @param num
     * @return
     */
    public int getLagestInFamily(int num) {
        int[] nums = new int[10];

        while (num > 0) {
            nums[num % 10]++;
            num /= 10;
        }

        long largest = 0;
        for (int i = 9; i >= 0; i--) {
            while (nums[i] > 0) {
                largest = largest * 10 + i;

                if (largest > 100000000) {
                    return -1;
                }

                nums[i]--;
            }
        }

        return (int) largest;
    }
}
