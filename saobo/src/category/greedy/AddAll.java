package category.greedy;

import java.util.PriorityQueue;

/**
 * uva 10954 - Add All There are n numbers. We need to get the sum of n numbers with least cost. The cost for each add
 * operation is defined as the sum of the numbers. i.e the cost of 1 + 2 is 3. If you want to add 1, 2 and 3. There are
 * several ways – No.1 1 + 2 = 3, cost = 3 3 + 3 = 6, cost = 6 Total = 9 No.2 1 + 3 = 4, cost = 4 2 + 4 = 6, cost = 6
 * Total = 10 No.3 2 + 3 = 5, cost = 5 1 + 5 = 6, cost = 6 Total = 11 Try to get the least cost for the n numbers.
 *
 * @author boyi
 */
public class AddAll {

    public static void main(String[] args) {
        Long[] numbers = { (long) 1, (long) 3, (long) 2, (long) 4 };

        AddAll addAll = new AddAll(numbers);
        System.out.println(addAll.getCost());
    }

    private PriorityQueue<Long> queue;

    public AddAll(Long[] numbers) {
        queue = new PriorityQueue<Long>(numbers.length, (num1, num2) -> Long.compare(num1, num2));

        for (Long number : numbers) {
            queue.offer(number);
        }
    }

    public Long getCost() {
        Long cost = (long) 0;

        while (queue.size() > 1) {
            Long first = queue.remove();
            Long second = queue.remove();
            Long currentCost = first + second;
            cost += currentCost;
            queue.offer(currentCost);
        }

        return cost;
    }

}
