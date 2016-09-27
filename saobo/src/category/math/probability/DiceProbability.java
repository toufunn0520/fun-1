package category.math.probability;

public class DiceProbability {

    public static void main(String[] args) {
        for (int i = 2; i < 10; i++)
            System.out.println(new DiceProbability().calculateExpectationForAnyKConsecutivePoint(i, 0.00000001));

    }

    /**
     * The fist time has k consecutive point.
     * 
     * @param k
     * @param precision
     * @return
     */
    public double calculateExpectationForAnyKConsecutivePoint(int k, double precision) {
        if (k < 2) {
            return 1;
        }

        double probabilyOfPoint = 1.0 / 6;

        double kthProbability = Math.pow(probabilyOfPoint, k - 1);
        double kPlusOneProbability = (1 - probabilyOfPoint) * kthProbability;

        int i = k;
        double ithProbability = kthProbability;
        double currentSum = kthProbability;
        double[] prevKprobSum = new double[k];
        prevKprobSum[i % k] = currentSum;
        double expectation = i * ithProbability;

        do {
            i++;
            int index = i % k;
            ithProbability = (1 - prevKprobSum[index]) * kPlusOneProbability;
            currentSum += ithProbability;
            prevKprobSum[index] = currentSum;
            expectation += ithProbability * i;
        } while (ithProbability * i > precision && i < Integer.MAX_VALUE);

        return expectation;
    }
}
