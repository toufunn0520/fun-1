package category.container.heap;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class IndexPair {

    int indexOfArray;

    int indexOfList;

    public IndexPair(int indexOfList, int indexOfArray) {
        this.indexOfArray = indexOfArray;
        this.indexOfList = indexOfList;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(indexOfList).append(":").append(indexOfArray).toString();
    }
}

class Range {

    int end;

    int start;

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(start).append("-").append(end).toString();
    }
}

public class SmallestRange {

    public static void main(String[] args) {
        Integer[] num1 = { 4, 10, 15, 24, 26 };
        Integer[] num2 = { 0, 9, 12, 20 };
        Integer[] num3 = { 5, 18, 22, 30 };

        List<Integer[]> numbers = Arrays.asList(num1, num2, num3);

        System.out.println(new SmallestRange().getSmallestRange(numbers));

    }

    /**
     * <pre>
     * You have k lists of sorted integers. Find the smallest range that includes at least one number from each of the k lists.
     * 
     * For example,
     * List 1: [4, 10, 15, 24, 26]
     * List 2: [0, 9, 12, 20]
     * List 3: [5, 18, 22, 30]
     * 
     * The smallest range here would be [20, 24] as it contains 24 from list 1, 20 from list 2, and 22 from list 3.
     * </pre>
     *
     * @param numbers
     * @return
     */
    public Range getSmallestRange(List<Integer[]> numbers) {
        if (numbers == null || numbers.size() == 0) {
            return null;
        }

        PriorityQueue<IndexPair> queue = new PriorityQueue<IndexPair>(numbers.size(),
                (pair1, pair2) -> Integer.compare(numbers.get(pair1.indexOfList)[pair1.indexOfArray],
                        numbers.get(pair2.indexOfList)[pair2.indexOfArray]));

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.size(); i++) {
            queue.offer(new IndexPair(i, 0));
            max = Math.max(max, numbers.get(i)[0]);
            min = Math.min(min, numbers.get(i)[0]);
        }

        Range minRange = new Range(min, max);

        while (queue.size() == numbers.size()) {
            IndexPair peekPair = queue.poll();
            min = numbers.get(peekPair.indexOfList)[peekPair.indexOfArray];

            if (max - min < minRange.end - minRange.start) {
                minRange.start = min;
                minRange.end = max;
            }

            peekPair.indexOfArray++;

            // run out of one array
            if (peekPair.indexOfArray >= numbers.get(peekPair.indexOfList).length) {
                break;
            }

            max = Math.max(max, numbers.get(peekPair.indexOfList)[peekPair.indexOfArray]);
            queue.add(new IndexPair(peekPair.indexOfList, peekPair.indexOfArray));
        }

        return minRange;
    }
}
