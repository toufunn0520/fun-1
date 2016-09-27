package category.greedy;

public class CoinGame {

    public int getLargestAdjancy(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }

        int prev = array[0];
        int adjancy = 0;
        int improveByTwo = 0;
        int improveByOne = 0;
        int nochange = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == prev) {
                adjancy++;
            } else {
                if (i + 1 < array.length && array[i + 1] == prev) {
                    improveByTwo++;
                } else if (i == array.length - 1 || i == 1) {
                    improveByOne++;
                } else {
                    nochange++;
                }
            }
            prev = array[i];
        }

        if (improveByTwo > 0) {
            return adjancy + 2;
        } else if (improveByOne > 0) {
            return adjancy + 1;
        } else if (nochange > 0) {
            return adjancy;
        } else {
            return adjancy - 1;
        }
    }
}
