package category.math.probability;

public class ConsecutiveProbability {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            System.out.println(new ConsecutiveProbability(1.0 / 6).caculateExpectationForKConsecutiveWithSpecificPoint(
                    i, 0.000000001));

    }

    private double probabilyOfPoint;

    public ConsecutiveProbability(double probabiityOfPoint) {
        if (probabiityOfPoint < 0) {
            throw new RuntimeException();
        }

        this.probabilyOfPoint = probabiityOfPoint;
    }

    /**
     * [Google] Caculate expection when first k consecutive head shows up.
     *
     * @param k
     * @param precision
     * @return
     */
    public double caculateExpectationForKConsecutiveWithSpecificPoint(int k, double precision) {
        double kthProbability = Math.pow(probabilyOfPoint, k);
        double kPlusOneProbability = (1 - probabilyOfPoint) * kthProbability;

        int i = k;
        double ithProbability = kthProbability;
        double currentSum = kthProbability;
        double[] prevKprobSum = new double[k + 1];
        prevKprobSum[i % (k + 1)] = currentSum;
        double expectation = i * ithProbability;

        do {
            i++;
            int index = i % (k + 1);
            ithProbability = (1 - prevKprobSum[index]) * kPlusOneProbability;
            currentSum += ithProbability;
            prevKprobSum[index] = currentSum;
            expectation += ithProbability * i;
        } while (ithProbability * i > precision && i < Integer.MAX_VALUE);

        return expectation;
    }

}
