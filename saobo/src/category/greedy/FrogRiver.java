package category.greedy;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class FrogRiver {

    /**
     * <pre>
     * - A bug is trying to cross the river start from position 0 to position X
     * - Every time bug can jump no more than the D steps (1 to D steps);
     * - Leaves will fall from the tree to the river based on schedule A. A[0] = 1
     * - means a leaf will fall on position 1 at time 0;
     * - Need to find the earliest time that the bug can jump from position 0 to x
     * - using leaves; If there is no answer, return -1;
     * </pre>
     *
     * @param distance
     * @param num
     * @return
     */
    public static int getMinTimeSlots(int length, int[] leafDropSequence, int distance) {
        if (distance > length) {
            return 1;
        }

        int[] leafShowsupTime = new int[length + 1];
        Arrays.fill(leafShowsupTime, -1);

        leafShowsupTime[0] = 0;
        for (int i = 0; i < leafDropSequence.length; i++) {
            if (leafShowsupTime[leafDropSequence[i]] == -1) {
                leafShowsupTime[leafDropSequence[i]] = i;
            }
        }

        // assume the frog can jump more than once in one second as long as there is a leaf to stand on
        int currentIndex = 0;
        int currentTime = 0;
        while (currentIndex + distance < length) {
            int nextIndex = -1;
            int nextTime = currentTime + 1;

            for (int i = currentIndex + 1; i <= currentIndex + distance; i++) {
                if (leafShowsupTime[i] != -1 && (nextIndex == -1 || leafShowsupTime[i] <= nextTime)) {
                    nextIndex = i;
                    nextTime = Math.max(currentTime + 1, leafShowsupTime[i]);
                }
            }

            if (nextIndex == -1) {
                // means the frog cannot proceed in current position. It will never make it.
                currentTime = -1;
                break;
            } else {
                currentIndex = nextIndex;
                currentTime = nextTime;
            }
        }

        return currentTime;
    }

    public static void main(String[] args) {
        int[] A = { 1, 3, 1, 4, 2, 5 };
        int X = 7;
        int D = 3;

        System.out.println(getMinTimeSlots(7, A, 3));
    }

    @Test
    public void testWhenFrogCanMakeIt() {
        int[] leafDropSequence1 = { 1, 1, 1 };
        int[] leafDropSequence2 = { 1, 2, 3 };
        int[] leafDropSequence3 = { 1, 3, 1, 4, 2, 5 };
        int[] leafDropSequence4 = { 1, 3, 1, 1, 1, 1, 2, 2, 1, 2, 2, 5 };

        Assert.assertEquals(1, getMinTimeSlots(9, leafDropSequence1, 10));
        Assert.assertEquals(2, getMinTimeSlots(3, leafDropSequence2, 1));
        Assert.assertEquals(3, getMinTimeSlots(7, leafDropSequence3, 3));
        Assert.assertEquals(11, getMinTimeSlots(7, leafDropSequence4, 3));
    }

    @Test
    public void testWhenFrogCannotMakeIt() {
        int[] leafDropSequence1 = { 1, 1, 1 }; // stopped at spot 1
        int[] leafDropSequence2 = { 1, 2, 3 }; // stopped at spot 3
        int[] leafDropSequence3 = { 1, 2, 3, 7, 8, 9 }; // stopped at spot 3

        Assert.assertEquals(-1, getMinTimeSlots(10, leafDropSequence1, 1));
        Assert.assertEquals(-1, getMinTimeSlots(10, leafDropSequence2, 6));
        Assert.assertEquals(-1, getMinTimeSlots(10, leafDropSequence3, 3));
    }

}
