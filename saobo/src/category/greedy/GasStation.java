package category.greedy;

public class GasStation {

    /**
     * [Leetcode 134] https://leetcode.com/problems/gas-station/
     *
     * <pre>
     * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
     * You begin the journey with an empty tank at one of the gas stations.
     * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
     * 
     * Note:
     * The solution is guaranteed to be unique.
     * </pre>
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0) {
            return -1;
        }

        int currentGas = 0;
        int startIndex = -1;
        for (int i = 0; i < gas.length; i++) {
            gas[i] = gas[i] - cost[i];

            currentGas += gas[i];

            if (startIndex == -1 && gas[i] >= 0) {
                startIndex = i;
                currentGas = gas[i];
            }

            if (startIndex != -1 && currentGas < 0) {
                startIndex = -1;
                currentGas = 0;
            }
        }

        if (startIndex == -1) {
            return -1;
        } else {
            for (int i = 0; i < startIndex; i++) {
                currentGas += gas[i];
                if (currentGas < 0) {
                    return -1;
                }
            }
        }

        return startIndex;
    }
}
