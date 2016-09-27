package category.container.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MedianOfAStream {

    /**
     * [Snap*chat]Median of Integer Stream.
     *
     * @param nums
     * @return
     */
    public static List<Integer> getMedian(List<Integer> nums) {

        List<Integer> result = new ArrayList<Integer>();

        if (nums == null || nums.size() == 0) {
            return result;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((int1, int2) -> (Integer.compare(int2, int1)));
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((int1, int2) -> (Integer.compare(int1, int2)));

        maxHeap.offer(nums.get(0));
        result.add(nums.get(0));
        int i = 1;
        for (i = 1; i < nums.size(); i++) {

            int current = nums.get(i);

            if (maxHeap.size() > minHeap.size()) {
                if (current >= maxHeap.peek()) {
                    minHeap.offer(current);
                } else {
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(current);
                }
            } else {
                if (current > minHeap.peek()) {
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(current);
                } else {
                    maxHeap.offer(current);
                }
            }

            if (maxHeap.size() == minHeap.size()) {
                result.add((maxHeap.peek() + minHeap.peek()) / 2);
            } else {
                result.add(maxHeap.peek());
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 3, 2, 50, 1, 9, 10, 20, 15, 4);
        List<Integer> result = getMedian(numbers);

        for (int i : result) {
            System.out.print(i + " ");
        }

    }

}
