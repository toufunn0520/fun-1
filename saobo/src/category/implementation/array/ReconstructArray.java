package category.implementation.array;

import java.util.LinkedList;
import java.util.List;

public class ReconstructArray {

    public static void main(String[] args) {

        int[] numOfBiggerOnleft = { 0, 0, 2, 3, 1 };
        int[] result = reconstruct(numOfBiggerOnleft, 5);

        for (int i : result) {
            System.out.print("" + i + " ");
        }
    }

    /**
     * [Google onsite] 给一个array比如[4,2,1,3,5],根据这个array现在我们能有了一个新的array => 每个数是在原array里,
     * 在它左边的所有比它大的number的个数,就是[0,1,2,1,0]. 题目是现在给了这个[0,1,2,1,0]要求原array, 原来array的range是1~n
     *
     * @param numOfBiggerOnleft
     * @param n
     * @return
     */
    public static int[] reconstruct(int[] numOfBiggerOnleft, int n) {
        if (numOfBiggerOnleft.length != n || n <= 0) {
            throw new RuntimeException("Illegal Arguments");
        }

        int[] number = new int[n];

        List<Integer> indices = new LinkedList<Integer>();

        for (int i = 0; i < numOfBiggerOnleft.length; i++) {
            indices.add(indices.size() - numOfBiggerOnleft[i], i);
        }

        int i = 0;
        for (int index : indices) {
            number[index] = ++i;
        }

        return number;
    }

}
