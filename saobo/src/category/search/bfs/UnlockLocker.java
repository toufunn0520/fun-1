package category.search.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class UnlockLocker {

    private static List<Integer> getDigits(int number) {
        List<Integer> digits = new ArrayList<Integer>();
        while (number != 0) {
            digits.add(0, number % 10);
            number /= 10;
        }

        return digits;
    }

    private static List<Integer> getNextState(int current, Set<Integer> blockStates) {
        List<Integer> nextStates = new ArrayList<Integer>();

        List<Integer> digits = getDigits(current);

        for (int i = 0; i < digits.size(); i++) {
            int digit = digits.get(i);

            digits.set(i, (digit + 1) % 10);
            int state1 = getNumber(digits);
            digits.set(i, (digit + 9) % 10);
            int state2 = getNumber(digits);
            digits.set(i, digit);

            if (!blockStates.contains(state1)) {
                nextStates.add(state1);
            }

            if (!blockStates.contains(state2)) {
                nextStates.add(state2);
            }
        }

        return nextStates;
    }

    private static int getNumber(List<Integer> digits) {
        int number = 0;
        for (int digit : digits) {
            number = number * 10 + digit;
        }
        return number;
    }

    /**
     * [Google onsite] 假设每次密码锁只能移动一格，求从初始状态到unlock状态的解锁序列. 在序列中不可出现blocking state 找到一组答案即可
     *
     * @param blockStates
     * @param initailState
     * @param unlockState
     * @return
     */
    public static List<Integer> getUnlockSequence(Set<Integer> blockStates, int initailState, int unlockState) {
        List<Integer> result = new ArrayList<Integer>();

        Set<Integer> visited = new HashSet<Integer>();
        Map<Integer, Integer> child2Parent = new HashMap<Integer, Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(initailState);
        visited.add(initailState);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == unlockState) {
                break;
            }

            List<Integer> nextStates = getNextState(current, blockStates);

            for (int nextState : nextStates) {
                if (visited.add(nextState)) {
                    queue.offer(nextState);
                    child2Parent.put(nextState, current);
                }
            }
        }

        if (child2Parent.containsKey(unlockState)) {
            int currentState = unlockState;
            while (child2Parent.containsKey(currentState)) {
                currentState = child2Parent.get(currentState);
                result.add(0, currentState);
            }

            result.add(unlockState);
        }

        return result;
    }

    public static void main(String[] args) {
        Set<Integer> blockStates = new HashSet<Integer>(Arrays.asList(123));

        System.out.println(getUnlockSequence(blockStates, 223, 124));

    }

}
