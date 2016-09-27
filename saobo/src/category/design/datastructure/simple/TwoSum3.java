package category.design.datastructure.simple;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TwoSum3 {

    private Map<Integer, Integer> nums;

    public TwoSum3() {
        nums = new HashMap<Integer, Integer>();
    }

    public void add(int number) {
        if (nums.containsKey(number)) {
            nums.put(number, nums.get(number) + 1);
        } else {
            nums.put(number, 1);
        }
    }

    /**
     * [Leetcode 170] https://leetcode.com/problems/two-sum-iii-data-structure-design/
     * 
     * <pre>
     * Design and implement a TwoSum class. It should support the following operations: add and find.
     *
     * add - Add the number to an internal data structure.
     * find - Find if there exists any pair of numbers which sum is equal to the value.
     *
     * For example,
     * add(1); add(3); add(5);
     * find(4) -> true
     * find(7) -> false
     * </pre>
     *
     * @param value
     * @return
     */
    public boolean find(int value) {
        Iterator<Integer> it = nums.keySet().iterator();
        while (it.hasNext()) {
            int current = it.next();
            int res = value - current;
            if (res == current) {
                if (nums.get(res) != null && nums.get(res) > 1) {
                    return true;
                }
            } else if (nums.containsKey(res)) {
                return true;
            }
        }
        return false;
    }
}
