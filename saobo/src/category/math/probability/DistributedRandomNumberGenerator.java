package category.math.probability;

import java.util.HashMap;

public class DistributedRandomNumberGenerator {

    public static void main(String[] args) {
        DistributedRandomNumberGenerator generator = new DistributedRandomNumberGenerator();
        generator.addNumber(1, 0.2d);
        generator.addNumber(2, 0.3d);
        generator.addNumber(5, 0.5d);

        int testCount = 1000000;

        HashMap<Integer, Double> test = new HashMap<>();

        for (int i = 0; i < testCount; i++) {
            int random = generator.getDistributedRandomNumber();
            test.put(random, (test.get(random) == null) ? (1d / testCount) : test.get(random) + 1d / testCount);
        }

        System.out.println(test.toString());
    }

    private HashMap<Integer, Double> distribution;

    private double distSum;

    public DistributedRandomNumberGenerator() {
        distribution = new HashMap<>();
    }

    public void addNumber(int value, double distribution) {
        if (this.distribution.containsKey(value)) {
            distSum -= this.distribution.get(value);
        }
        this.distribution.put(value, distribution);
        distSum += distribution;
    }

    public int getDistributedRandomNumber() {
        double rand = Math.random();

        double tempDist = 0;
        for (Integer i : distribution.keySet()) {
            tempDist += distribution.get(i);
            if (rand * distSum <= tempDist) {
                return i;
            }
        }

        throw new RuntimeException("Failed to get the distributed random numbers");
    }

}
