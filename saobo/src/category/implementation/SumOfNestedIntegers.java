package category.implementation;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import category.container.stack.BaseNestedInteger;
import category.container.stack.NestedInteger;

/**
 * [LinkedIn phone] Given a nested list of integers, returns the sum of all integers in the list weighted by their depth
 * For example, given the list {{1,1},2,{1,1}} the function should return 10 (four 1's at depth 2, one 2 at depth 1)
 * Given the list {1,{4,{6}}} the function should return 27 (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3)
 */
public class SumOfNestedIntegers {

    public int depthSum(List<NestedInteger> list) {
        if (list == null || list.size() == 0) {
            return 0;
        }

        int sum = 0;
        Iterator<NestedInteger> iterator = list.iterator();

        while (iterator.hasNext()) {
            sum += getSumHelper(iterator.next(), 1);
        }

        return sum;

    }

    private int getSumHelper(NestedInteger integer, int weight) {
        NestedInteger current = integer;
        int sum = 0;
        if (!current.isInteger()) {
            List<NestedInteger> children = current.getList();
            for (NestedInteger child : children) {
                sum += getSumHelper(child, weight + 1);
            }
        } else {
            sum += integer.getInteger() * weight;
        }

        return sum;
    }

    @Test
    public void testWithIntegerAndListMixed() {
        NestedInteger[] integers = new NestedInteger[10];
        for (int i = 0; i < 10; i++) {
            integers[i] = new BaseNestedInteger(i + 1);
        }

        List list = Arrays.asList(new BaseNestedInteger(1), new BaseNestedInteger(Arrays.asList(integers)));

        Assert.assertEquals(111, new SumOfNestedIntegers().depthSum(list));
    }

    @Test
    public void testWithOnlyIntegers() {
        NestedInteger[] integers = new NestedInteger[10];
        for (int i = 0; i < 10; i++) {
            integers[i] = new BaseNestedInteger(i + 1);
        }

        List list = Arrays.asList(integers);

        Assert.assertEquals(55, new SumOfNestedIntegers().depthSum(list));
    }

    @Test
    public void testWithOnlyNestedIntegers() {
        NestedInteger[] integers = new NestedInteger[10];
        for (int i = 0; i < 10; i++) {
            integers[i] = new BaseNestedInteger(i + 1);
        }

        List list = Arrays.asList(new BaseNestedInteger(Arrays.asList(integers)),
                new BaseNestedInteger(Arrays.asList(integers)));

        Assert.assertEquals(220, new SumOfNestedIntegers().depthSum(list));
    }

}
