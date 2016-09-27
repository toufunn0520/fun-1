package category.bit.bitmap;

public class BitMap {

    /**
     * 2^6 = 64, a long has 64-bit in Java.
     */
    public static final int ADDRESS_BITS_PER_WORD = 6;

    private Long[] data;

    public BitMap(int maxNum) {
        if (maxNum < 0) {
            throw new RuntimeException("max number cannot be less than 0");
        }

        this.data = new Long[maxNum >> ADDRESS_BITS_PER_WORD];
    }

    public void add(int bitIndex) {
        int wordIndex = bitIndex >> ADDRESS_BITS_PER_WORD;
        data[wordIndex] |= 1L << bitIndex;
    }

    public boolean get(int bitIndex) {
        int wordIndex = bitIndex >> ADDRESS_BITS_PER_WORD;
        return data.length >= wordIndex && (data[wordIndex] & (1L << bitIndex)) != 0;
    }

}
