package category.container.hash;

import interview.utils.Point;

import java.util.HashMap;
import java.util.Map;

/**
 * Used for recording the slope.
 * 
 * @author boyi
 */
class Irreducible {

    int denominator;

    int numerator;

    public Irreducible(int denominator, int numerator) {
        if (numerator == 0) {
            this.numerator = 0;
            this.denominator = 0;
        } else {
            int gcd = gcd(denominator, numerator);
            this.denominator = denominator / gcd;
            this.numerator = numerator / gcd;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Irreducible)) {
            return false;
        } else {
            Irreducible irreducible = (Irreducible) o;

            return irreducible.denominator == denominator && irreducible.numerator == numerator;
        }

    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    @Override
    public int hashCode() {
        return denominator * 33 + numerator;
    }
}

public class MaxPoints {

    /**
     * [Leetcode 149] https://leetcode.com/problems/max-points-on-a-line/
     *
     * <pre>
     * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
     * </pre>
     *
     * @param points
     * @return
     */
    public int maxPoints(Point[] points) {
        if (points == null) {
            return 0;
        }

        Map<Irreducible, Integer> statistics = new HashMap<Irreducible, Integer>();
        int maxNumberOnALine = 0;
        for (int i = 0; i < points.length; i++) {
            statistics.clear();
            int numberOfDuplicates = 0;
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }

                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    numberOfDuplicates++;
                    continue;
                }

                Irreducible irreducible = new Irreducible(points[i].x - points[j].x, points[i].y - points[j].y);
                int count = 0;
                if (statistics.containsKey(irreducible)) {
                    count = statistics.get(irreducible);
                }

                statistics.put(irreducible, count + 1);
            }

            maxNumberOnALine = Math.max(maxNumberOnALine, numberOfDuplicates + 1);

            for (Map.Entry<Irreducible, Integer> entry : statistics.entrySet()) {
                int numberOfPointsOnALine = entry.getValue() + numberOfDuplicates + 1;
                maxNumberOnALine = Math.max(numberOfPointsOnALine, maxNumberOnALine);
            }
        }

        return maxNumberOnALine;
    }
}
