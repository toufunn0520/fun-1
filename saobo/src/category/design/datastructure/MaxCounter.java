package category.design.datastructure;

/**
 * [Uber] implement four operations, O(1) time complexity each.
 */
public interface MaxCounter {

    /**
     * Add value to the container.
     * 
     * @param value
     */
    void add(int value);

    /**
     * Get the count of the value.
     * 
     * @param value
     * @return
     */
    int getCount(int value);

    /**
     * Get current max count.
     * 
     * @return
     */
    int getMaxCount();

    /**
     * Remove current max count. If have more than one, just remove one
     */
    void removeMaxCount();
}
