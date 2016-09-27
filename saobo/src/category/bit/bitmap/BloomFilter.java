package category.bit.bitmap;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.Collection;

public class BloomFilter<E> {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        BloomFilter<String> bloomFilter = new BloomFilter<String>(10, 2);
        bloomFilter.add("sachin");
        bloomFilter.add("tendulkar");

        System.out.println(bloomFilter.contains("sachin"));
        System.out.println(bloomFilter.contains("tendulkar"));

        System.out.println(bloomFilter.contains("rahul"));
        System.out.println(bloomFilter.contains("dravid"));
    }

    private final BitSet bitSet;

    private final int hashFunctionCount;

    private final MessageDigest md5Digest;

    /**
     * Constructs an empty Bloom filter. The optimal number of hash functions (k) is estimated from the total size of
     * the Bloom and the number of expected elements.
     *
     * @param bitSetSize
     *            defines how many bits should be used in total for the filter.
     * @param expectedNumberOfElements
     *            defines the maximum number of elements the filter is expected to contain.
     * @throws NoSuchAlgorithmException
     */
    public BloomFilter(int bitSetSize, int expectedNumberOfElements) throws NoSuchAlgorithmException {
        bitSet = new BitSet(bitSetSize);
        /*
         * The natural logarithm is the logarithm to the base e, where e is an irrational and transcendental constant
         * approximately equal to 2.718281828.
         */
        hashFunctionCount = (int) Math.round((bitSetSize / (double) expectedNumberOfElements) * Math.log(2.0));
        md5Digest = java.security.MessageDigest.getInstance("MD5");
    }

    private void add(byte[] bytes) {
        int[] hashes = createHashes(bytes);
        for (int hash : hashes)
            bitSet.set(getBitIndex(hash), true);
    }

    /**
     * Adds an object to the Bloom filter. The output from the object's toString() method is used as input to the hash
     * functions.
     *
     * @param element
     *            is an element to register in the Bloom filter.
     */
    public void add(E element) {
        add(element.toString().getBytes());
    }

    /**
     * Adds all elements from a Collection to the Bloom filter.
     * 
     * @param c
     *            Collection of elements.
     */
    public void addAll(Collection<? extends E> c) {
        for (E element : c)
            add(element);
    }

    /**
     * Sets all bits to false in the Bloom filter.
     */
    public void clear() {
        bitSet.clear();
    }

    private boolean contains(byte[] bytes) {
        for (int hash : createHashes(bytes)) {
            if (!bitSet.get(getBitIndex(hash))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if the array of bytes could have been inserted into the Bloom filter.
     *
     * @param bytes
     *            array of bytes to check.
     * @return true if the array could have been inserted into the Bloom filter.
     */
    public boolean contains(E element) {
        return contains(element.toString().getBytes());
    }

    /**
     * Returns true if all the elements of a Collection could have been inserted into the Bloom filter.
     * 
     * @param c
     *            elements to check.
     * @return true if all the elements in c could have been inserted into the Bloom filter.
     */
    public boolean containsAll(Collection<? extends E> c) {
        for (E element : c)
            if (!contains(element))
                return false;
        return true;
    }

    /**
     * Generates digests based on the contents of an array of bytes and splits the result into 4-byte int's and store
     * them in an array. The digest function is called until the required number of int's are produced. For each call to
     * digest a salt is prepended to the data. The salt is increased by 1 for each call.
     *
     * @param data
     *            specifies input data.
     * @param hashes
     *            number of hashes/int's to produce.
     * @return array of int-sized hashes
     */
    private int[] createHashes(byte[] data) {
        int[] result = new int[hashFunctionCount];

        int k = 0;
        byte salt = 0;
        while (k < hashFunctionCount) {
            byte[] digest;
            synchronized (md5Digest) {
                md5Digest.update(salt);
                salt++;
                digest = md5Digest.digest(data);
            }

            /*
             * we divide an array into blocks of 4 for example: - 100 length digest array is broken into pieces of 25
             * But i advances by 4, not by 25.
             */
            for (int i = 0; i < digest.length / 4 && k < hashFunctionCount; i++) {
                int h = 0;
                // 4 bits are consumed for a single hash.
                for (int j = (i * 4); j < (i * 4) + 4; j++) {
                    h <<= 8;
                    h |= (digest[j]) & 0xFF;
                }
                result[k] = h;
                k++;
            }
        }
        return result;
    }

    private int getBitIndex(int hash) {
        return Math.abs(hash % bitSet.size());
    }
}
