package interview.utils;

import java.util.List;

public class ListUtils {

    public static void listPrint(List list) {
        for (Object i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void listsPrint(List<List<Integer>> lists) {
        for (List<Integer> list : lists) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
