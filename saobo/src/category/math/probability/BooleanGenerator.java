package category.math.probability;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BooleanGenerator {

    public static void main(String[] args) {
        int testCount = 10000000;

        Map<Boolean, Double> results = new HashMap<Boolean, Double>();
        results.put(true, 0d);
        results.put(false, 0d);

        BooleanGenerator generator = new BooleanGenerator();

        for (int i = 0; i < testCount; i++) {
            boolean result = generator.getTrueWithProbability(10d);
            results.put(result, results.get(result) + 1d / testCount);
        }

        System.out.println(results.toString());

    }

    /**
     * write a function f(x), so that f(x) returns true with x% probability.
     * 
     * @param x
     * @return
     */
    public boolean getTrueWithProbability(double x) {
        double random = new Random().nextDouble();

        if (random <= x / 100) {
            return true;
        } else {
            return false;
        }

    }

}
