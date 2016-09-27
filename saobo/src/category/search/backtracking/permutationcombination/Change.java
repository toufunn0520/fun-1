package category.search.backtracking.permutationcombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Change {

    private void changeHelper(int remaining, int[] possibleChanges, List<Integer> currentCombination, int startIndex,
            List<List<Integer>> combinations) {
        if (remaining == 0) {
            combinations.add(new ArrayList<Integer>(currentCombination));
            return;
        }

        for (int i = startIndex; i < possibleChanges.length; i++) {
            if (remaining - possibleChanges[i] >= 0) {
                currentCombination.add(possibleChanges[i]);
                changeHelper(remaining - possibleChanges[i], possibleChanges, currentCombination, i, combinations);
                currentCombination.remove(currentCombination.size() - 1);
            } else {
                break;
            }
        }
    }

    /**
     * http://www.glassdoor.com/Interview/-VARIATION-OF-Given-a-set-of-possible-change-such-as-1-5-10-25-and-an-amount-
     * such-as-13-return-all-possible-ways-QTN_852303.htm Given a set of possible change, such as {1, 5, 10, 25}, and an
     * amount such as 13, return all possible ways you can give change back to someone.
     *
     * @param sum
     * @param possibleChanges
     * @return
     */
    public List<List<Integer>> getChanges(int sum, int[] possibleChanges) {
        List<List<Integer>> combinations = new ArrayList<List<Integer>>();
        List<Integer> currentCombination = new ArrayList<Integer>();

        Arrays.sort(possibleChanges);

        changeHelper(sum, possibleChanges, currentCombination, 0, combinations);

        return combinations;
    }
}
