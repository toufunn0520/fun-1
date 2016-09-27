package category.implementation.array;

public class RearrangeArray {

    public static void main(String[] args) {
        int[] testData = { 1, 3, 8, 5, 6 };
        int[] testIndex = { 2, 3, 1, 4, 0 };

        reArrange(testData, testIndex);

        for (int num : testData) {
            System.out.println(num);
        }
    }

    /**
     * Rearrange the array according to the index array.
     *
     * @param array
     * @param index
     */
    public static void reArrange(int[] array, int[] index) {
        if (index == null || index.length < 2)
            return;

        for (int i = 0; i < array.length;) {
            if (i != index[i]) {
                swap(array, i, index[i]);
                swap(index, i, index[i]);
            } else {
                i++;
            }
        }
    }

    public static void swap(int[] array, int index1, int index2) {
        if (array == null)
            return;
        if (index1 >= array.length || index2 >= array.length)
            throw new RuntimeException();

        array[index1] += array[index2];
        array[index2] = array[index1] - array[index2];
        array[index1] -= array[index2];
    }
}
