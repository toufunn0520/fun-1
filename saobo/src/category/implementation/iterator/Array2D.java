package category.implementation.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class Array2D<T> {

    private class array2DIterator implements Iterator<T> {

        List<T> curArray;

        int currentCol;

        int currentRow;

        public array2DIterator() {
            currentRow = 0;
            currentCol = 0;
        }

        @Override
        public boolean hasNext() {
            while (currentRow < array2d.size()) {
                curArray = array2d.get(currentRow);

                if (curArray != null && curArray.size() > 0) {
                    break;
                } else {
                    currentRow++;
                }
            }

            if (curArray == null) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public T next() {
            if (curArray == null) {
                throw new RuntimeException("No next element");
            }

            T next = curArray.get(currentCol++);

            if (currentCol == curArray.size()) {
                currentCol = 0;

                if (++currentRow == array2d.size()) {
                    curArray = null;
                }

                while (currentRow < array2d.size()) {
                    curArray = array2d.get(currentRow);

                    if (curArray != null && curArray.size() > 0) {
                        break;
                    } else {
                        currentRow++;
                    }
                }
            }

            return next;
        }
    }

    public static void main(String[] args) {
        Array2D<Integer> array2D = new Array2D<Integer>();
        array2D.addLine(Arrays.asList(1, 3, 4, 5));
        array2D.addLine(null);
        array2D.addLine(new ArrayList<Integer>());
        array2D.addLine(Arrays.asList(100, 200));
        array2D.addLine(new ArrayList<Integer>());
        array2D.addLine(Arrays.asList(100, 200));

        Iterator<Integer> it = array2D.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }

    List<List<T>> array2d;

    public Array2D() {
        array2d = new ArrayList<List<T>>();
    }

    public void addLine(List<T> nums) {
        if (nums != null) {
            array2d.add(new ArrayList<T>(nums));
        } else {
            array2d.add(null);
        }
    }

    public Iterator<T> iterator() {
        return new array2DIterator();
    }
}
