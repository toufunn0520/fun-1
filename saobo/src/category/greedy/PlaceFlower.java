package category.greedy;

import java.util.List;

public class PlaceFlower {

    /**
     * 如果flowerbed当中为true，说明已经栽过花了，附近两个不能再栽花。numberToPlace代表想再栽多少花到flowerbed里。让return是不是还能栽那么多谢花进去。
     *
     * @param flowerbed
     * @param numberToPlace
     * @return
     */
    public static boolean canPlaceFlowers(List<Boolean> flowerbed, int numberToPlace) {
        if (flowerbed == null) {
            return false;
        }

        int count = 0;
        int i = 0;
        while (i < flowerbed.size()) {
            if (!flowerbed.get(i) && (i == 0 || !flowerbed.get(i - 1))
                    && (i + 1 == flowerbed.size() || !flowerbed.get(i + 1))) {
                count++;
                i = +2;
            } else {
                i++;
            }
        }

        return numberToPlace <= count;
    }
}
