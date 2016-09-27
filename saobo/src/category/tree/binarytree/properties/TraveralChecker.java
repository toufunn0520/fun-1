package category.tree.binarytree.properties;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

public class TraveralChecker {

    public static void main(String[] args) {
        int[] array = { 1, 3, 4, 2 };
        System.out.println(new TraveralChecker().verifyPreorder(array));

    }

    /**
     * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
     * You may assume each number in the sequence is unique.
     *
     * @param preorder
     * @return
     */
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return false;
        }

        int low = Integer.MIN_VALUE, i = -1;

        for (int num : preorder) {
            if (num < low) {
                return false;
            }

            while (i >= 0 && num > preorder[i]) {
                low = preorder[i];
                i--;
            }

            i++;
            preorder[i] = num;
        }

        return true;
    }

    public boolean verifyPreorderNaive(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return false;
        }

        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> inorder = new Stack<Integer>();
        for (int num : preorder) {
            if (!inorder.isEmpty() && num < inorder.peek()) {
                return false;
            }

            while (!stack.isEmpty() && num > stack.peek()) {
                inorder.push(stack.pop());
            }
            stack.push(num);
        }

        return true;
    }

    public boolean verifyPreorderNaiveImproved(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return false;
        }

        Stack<Integer> stack = new Stack<Integer>();
        int currentMin = Integer.MIN_VALUE;
        for (int num : preorder) {
            if (currentMin > Integer.MIN_VALUE && num < currentMin) {
                return false;
            }

            while (!stack.isEmpty() && num > stack.peek()) {
                currentMin = stack.pop();
            }
            stack.push(num);
        }

        return true;
    }

    @Test
    public void testWithCases() {
        TraveralChecker checker = new TraveralChecker();
        int[] array1 = { 1 };
        int[] array2 = { 1, 2 };
        int[] array3 = { 2, 1, 3 };
        int[] array4 = { 1, 2, 4, 8, 10 };
        int[] array5 = { 1, 2, 4, 3, 5 };
        int[] array6 = { 1, 3, 4, 2 };
        int[] array7 = { 10, 2, 3, 4, 12, 14, 1 };

        Assert.assertEquals(true, checker.verifyPreorder(array1));
        Assert.assertEquals(true, checker.verifyPreorder(array2));
        Assert.assertEquals(true, checker.verifyPreorder(array3));
        Assert.assertEquals(true, checker.verifyPreorder(array4));
        Assert.assertEquals(true, checker.verifyPreorder(array5));
        Assert.assertEquals(false, checker.verifyPreorder(array6));
        Assert.assertEquals(false, checker.verifyPreorder(array7));
    }

    @Test
    public void testWithCasesNaive() {
        TraveralChecker checker = new TraveralChecker();
        int[] array1 = { 1 };
        int[] array2 = { 1, 2 };
        int[] array3 = { 2, 1, 3 };
        int[] array4 = { 1, 2, 4, 8, 10 };
        int[] array5 = { 1, 2, 4, 3, 5 };
        int[] array6 = { 1, 3, 4, 2 };
        int[] array7 = { 10, 2, 3, 4, 12, 14, 1 };

        Assert.assertEquals(true, checker.verifyPreorderNaive(array1));
        Assert.assertEquals(true, checker.verifyPreorderNaive(array2));
        Assert.assertEquals(true, checker.verifyPreorderNaive(array3));
        Assert.assertEquals(true, checker.verifyPreorderNaive(array4));
        Assert.assertEquals(true, checker.verifyPreorderNaive(array5));
        Assert.assertEquals(false, checker.verifyPreorderNaive(array6));
        Assert.assertEquals(false, checker.verifyPreorderNaive(array7));
    }

    @Test
    public void testWithCasesNaiveImproved() {
        TraveralChecker checker = new TraveralChecker();
        int[] array1 = { 1 };
        int[] array2 = { 1, 2 };
        int[] array3 = { 2, 1, 3 };
        int[] array4 = { 1, 2, 4, 8, 10 };
        int[] array5 = { 1, 2, 4, 3, 5 };
        int[] array6 = { 1, 3, 4, 2 };
        int[] array7 = { 10, 2, 3, 4, 12, 14, 1 };

        Assert.assertEquals(true, checker.verifyPreorderNaiveImproved(array1));
        Assert.assertEquals(true, checker.verifyPreorderNaiveImproved(array2));
        Assert.assertEquals(true, checker.verifyPreorderNaiveImproved(array3));
        Assert.assertEquals(true, checker.verifyPreorderNaiveImproved(array4));
        Assert.assertEquals(true, checker.verifyPreorderNaiveImproved(array5));
        Assert.assertEquals(false, checker.verifyPreorderNaiveImproved(array6));
        Assert.assertEquals(false, checker.verifyPreorderNaiveImproved(array7));
    }

}
