package category.math.number;

public class WholeSquare {

    /**
     * An integer P is a whole square if it is a square of some integer Q; i.e. if P = Q^2. Returns the number of whole
     * squares within the interval [A..B] (both ends included).
     * 
     * @param a
     * @param b
     * @return
     */
    public int getNumberOfWhileSquare(int a, int b) {
        if (b < 0) {
            return 0;
        }

        if (a < 0) {
            getNumberOfWhileSquare(0, b);
        }

        int sqrtA = (int) Math.sqrt(a);
        if (sqrtA * sqrtA < a) {
            sqrtA++;
        }

        int sqrtB = (int) Math.sqrt(b);

        return sqrtB - sqrtA + 1;
    }
}
