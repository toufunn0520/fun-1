package category.math;

public class RectangleArea {

    /**
     * [Leetcode 223] https://leetcode.com/problems/rectangle-area/
     * 
     * <pre>
     * Find the total area covered by two rectilinear rectangles in a 2D plane.
     * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
     * </pre>
     *
     * @param A
     * @param B
     * @param C
     * @param D
     * @param E
     * @param F
     * @param G
     * @param H
     * @return
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (D - B) * (C - A);
        int area2 = (G - E) * (H - F);

        int commonArea = 0;
        int left = Math.max(A, E);
        int right = Math.min(C, G);
        int up = Math.min(D, H);
        int down = Math.max(B, F);

        if (right > left && up > down) {
            commonArea = (up - down) * (right - left);
        }

        return area1 + area2 - commonArea;
    }
}
