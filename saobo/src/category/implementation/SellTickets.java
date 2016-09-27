package category.implementation;

import java.util.Arrays;
import java.util.Collections;

class PartialSort_structured {

    class TicketsStatus {

        long profits;

        int remainingTickets;

        public TicketsStatus(long profits, int remainingTickets) {
            this.profits = profits;
            this.remainingTickets = remainingTickets;
        }
    }

    TicketsStatus getMaxProfits(Integer[] tickets, int leftIndex, int rightIndex, int currentSalesTarget) {
        long profits = 0;
        if (leftIndex + 1 == rightIndex) {
            int currentPriceDifference = tickets[leftIndex] - tickets[rightIndex];
            if (currentPriceDifference > 0) {
                int numOfTicketSoldPerStation = Math.min(currentPriceDifference, currentSalesTarget / rightIndex);
                currentSalesTarget -= numOfTicketSoldPerStation * rightIndex;
                profits += (long) rightIndex * numOfTicketSoldPerStation
                        * (2 * tickets[leftIndex] - numOfTicketSoldPerStation + 1) / 2;
                if (numOfTicketSoldPerStation < currentPriceDifference) {
                    profits += (long) (tickets[leftIndex] - numOfTicketSoldPerStation) * currentSalesTarget;
                    currentSalesTarget = 0;
                }
            }
        } else {
            int i = median3(tickets, leftIndex, rightIndex);
            TicketsStatus leftRes = getMaxProfits(tickets, leftIndex, i, currentSalesTarget);
            profits += leftRes.profits;
            currentSalesTarget = leftRes.remainingTickets;
            if (currentSalesTarget > 0) {
                TicketsStatus rightRes = getMaxProfits(tickets, i, rightIndex, currentSalesTarget);
                profits += rightRes.profits;
                currentSalesTarget = rightRes.remainingTickets;
            }
        }
        return new TicketsStatus(profits, currentSalesTarget);
    }

    private int median3(Integer[] tickets, int leftIndex, int rightIndex) {
        int midIndex = (leftIndex + rightIndex) / 2;
        if (tickets[midIndex] > tickets[leftIndex])
            swap(tickets, leftIndex, midIndex);
        if (tickets[rightIndex] > tickets[leftIndex])
            swap(tickets, leftIndex, rightIndex);
        if (tickets[rightIndex] > tickets[midIndex])
            swap(tickets, midIndex, rightIndex);
        // Place pivot at position right - 1
        int pivot = rightIndex - 1;
        swap(tickets, midIndex, pivot);

        int i = leftIndex, j = pivot;
        while (true) {
            while (i < j) {
                if (tickets[++i] < tickets[pivot]) {
                    i--;
                    break;
                }
                if (tickets[pivot] < tickets[--j]) {
                    j++;
                    break;
                }
            }
            while (i < j && tickets[++i] >= tickets[pivot]) {
            }
            while (i < j && tickets[pivot] >= tickets[--j]) {
            }
            if (i < j)
                swap(tickets, i, j);
            else
                break;
        }
        // Restore pivot
        swap(tickets, i, pivot);
        return i;
    }

    private void swap(Integer[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}

public class SellTickets {

    public static void main(String[] args) {
        Integer[] storage = { 8, 10, 15, 14, 18 };

        // System.out.println(new SellTickets().getMaxProfits(storage, 5));
        // System.out.println(new SellTickets().getMaxProfitsOptimal(storage, 5));
        System.out.println(new PartialSort_structured().getMaxProfits(storage, 0, storage.length - 1, 5).profits);
    }

    /**
     * [Zenefit]
     *
     * <pre>
     * 第一题叫sell tickets。火车站有一些买票点（station）。每个买票点有数量不同的存票。而且票的价格和此买票点卖票时的存票量相同。
     * 比如：A来点1，有15张票，那么票就卖A 15块。A之后B来了。此时点A只有14张票，就卖B 14块。火车站有个卖票点总指标，卖够了就收摊。问卖完这个指标的最大收益。
     * 输入是如下的两行。
     * 2， 4
     * 5， 2
     * 第一行第一个数是买票点（station）的数量，这里是2个。第二个数是总指标的值。这里是4张。
     * 第二行是各个买票点的最开始的存票数量。这里5张和2张。
     * 最大的收益是14。
     * 
     * 这个我觉得还不算难。不过要先想清楚到底是怎么卖票的。首先肯定永远卖票最多那个买票点的票，因为价格最高。而后当票卖到好几个买票点一样多的时候就是这些买票点轮流卖票了。
     * 
     * 比如如下情况：
     * 
     * 3， 5
     * 
     * 10， 8， 5
     * 
     * 买票的次序就是：10， 9， 8， 8， 7
     * </pre>
     *
     * @return
     */
    public int getMaxProfits(int[] ticketPrices, int targetTicketsNumber) {
        int numOfStations = ticketPrices.length;

        if (ticketPrices == null || ticketPrices.length == 0 || targetTicketsNumber <= 0) {
            return 0;
        }

        Arrays.sort(ticketPrices);

        int maxProfits = 0;
        int ticketCount = 0;
        int index = numOfStations - 1;
        int currentMaxPrice = ticketPrices[index];
        while (ticketCount < targetTicketsNumber) {
            if (ticketPrices[index] == currentMaxPrice) {
                maxProfits += ticketPrices[index];
                ticketPrices[index--]--;
                ticketCount++;
            } else {
                index = numOfStations - 1;
                currentMaxPrice = ticketPrices[index];
                if (currentMaxPrice == 0) {
                    break;
                }
            }

        }

        return maxProfits;
    }

    public long getMaxProfitsOptimal(Integer[] ticketPrices, int salesTarget) {
        int numOfStation = ticketPrices.length;
        if (numOfStation == 0 || salesTarget <= 0)
            return 0;

        Arrays.sort(ticketPrices, Collections.reverseOrder());
        ticketPrices = Arrays.copyOf(ticketPrices, ticketPrices.length + 1);

        long maxProfits = 0;
        int remainingNumberOfTickets = salesTarget, curIndex = 0;
        int numOfTicketSoldPerStation, currentPriceDifference, numOfStationsWithSamePrice;
        while (remainingNumberOfTickets > 0 && curIndex < numOfStation) {
            numOfStationsWithSamePrice = curIndex + 1;
            currentPriceDifference = ticketPrices[curIndex] - ticketPrices[numOfStationsWithSamePrice];
            if (currentPriceDifference > 0) {
                numOfTicketSoldPerStation = Math.min(currentPriceDifference, remainingNumberOfTickets
                        / numOfStationsWithSamePrice);
                remainingNumberOfTickets -= numOfTicketSoldPerStation * numOfStationsWithSamePrice;
                maxProfits += (long) numOfStationsWithSamePrice * numOfTicketSoldPerStation
                        * (2 * ticketPrices[curIndex] - numOfTicketSoldPerStation + 1) / 2;
                if (numOfTicketSoldPerStation < currentPriceDifference) {
                    maxProfits += (long) (ticketPrices[curIndex] - numOfTicketSoldPerStation)
                            * remainingNumberOfTickets;
                    remainingNumberOfTickets = 0;
                }
            }
            curIndex++;
        }
        return maxProfits;
    }

}
