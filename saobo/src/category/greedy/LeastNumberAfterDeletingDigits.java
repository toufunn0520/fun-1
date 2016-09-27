package category.greedy;

import java.util.LinkedList;
import java.util.List;

/**
 * [Google Interview] Problem: Please get the least number after deleting k digits from the input number. For example,
 * if the input number is 24635, the least number is 23 after deleting 3 digits.
 * 
 * @author boyi
 */
public class LeastNumberAfterDeletingDigits {

    public static void main(String[] args) {
        List<Integer> numbers = new LinkedList<Integer>();
        int[] testNumbers1 = { 6, 2, 1, 3, 4 }; // head will be deleted.
        int[] testNumbers2 = { 1, 2, 3, 4, 5 }; // increasing sequence
        int[] testNumbers3 = { 2, 4, 6, 3, 5 }; // the sequence would be
                                                // affected after deleting.

        for (int num : testNumbers1) {
            numbers.add(num);
        }

        System.out.println("The original list is " + numbers);
        List<Integer> result = new LeastNumberAfterDeletingDigits().findLeasetNumber(numbers, 3);
        System.out.println("The result is " + result);
    }

    /**
     * Basic idea is to remove the first numbersToRemove numbers that the next one is larger than itself. Otherwise,
     * delete the last few ascending numbers.
     * 
     * @param numbers
     * @param numbersToRemove
     * @return
     */
    public List<Integer> findLeasetNumber(List<Integer> numbers, int numbersToRemove) {
        if (numbers == null || numbers.isEmpty() || numbers.size() <= numbersToRemove) {
            return new LinkedList<Integer>();
        }

        int numbersRemoved = 0;
        int previousNumber = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) < previousNumber) {
                // The list has been changed. We need to back off 1 index
                numbers.remove(--i);
                // since the list has been changed. We should back off 1 index
                // if current index is not the head of the list.
                if (i >= 1)
                    i--;
                numbersRemoved++;
                if (numbersRemoved == numbersToRemove) {
                    break;
                }
            }

            previousNumber = numbers.get(i);
        }

        int numbersToRemoveFromEnd = numbersToRemove - numbersRemoved;
        for (int i = numbers.size() - numbersToRemoveFromEnd; i < numbers.size();) {
            numbers.remove(i);
        }

        return numbers;
    }

}
