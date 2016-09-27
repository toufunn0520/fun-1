package category.implementation.array;

import java.util.Arrays;

public class GroupElements {

    /**
     * [Google onsite] There are only 'a' and 'b' in the char array. you can flip 'a' to 'b' or 'b' to 'a' at cost 1
     * each time. Please achieve the final state so that all 'a's are in front of 'b' by flip the elements in the array
     * with min cost.
     *
     * @param number
     * @return
     */
    public static int flipElements(char[] number) {
        if (number == null || number.length == 0) {
            return 0;
        }

        int count = 0;
        int[] numOfBFromLeftExclude = new int[number.length];
        int[] numOfAFromRightInclude = new int[number.length];
        for (int i = 0; i < number.length; i++) {
            numOfBFromLeftExclude[i] = count;
            if (number[i] == 'b') {
                count++;
            }
        }

        count = 0;
        for (int i = number.length - 1; i >= 0; i--) {
            if (number[i] == 'a') {
                count++;
            }
            numOfAFromRightInclude[i] = count;

        }

        int minCost = number.length + 1;
        int minIndex = 0;

        for (int i = 0; i < number.length; i++) {
            // if before i are 'a', from i to end are 'b'
            int currentCost = numOfBFromLeftExclude[i] + numOfAFromRightInclude[i];

            if (currentCost < minCost) {
                minCost = currentCost;
                minIndex = i;
            }
        }

        Arrays.fill(number, 'b');
        for (int i = 0; i < minIndex; i++) {
            number[i] = 'a';
        }

        return minCost;
    }

    public static void main(String[] args) {
        char[] number = "ababbab".toCharArray();

        System.out.println(flipElements(number));
        System.out.println(new String(number));
    }
}
