package category.wrapper.iterator;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import category.implementation.iterator.ZigZagIterator;

public class ZigZagIteratorTest {

    @Test
    public void testMultipleListsWithDifferentSizes() {
        List<Integer> list1 = Arrays.asList(1, 2);
        List<Integer> list2 = Arrays.asList(10);
        List<Integer> list3 = Arrays.asList(100, 200, 300);
        List<List<Integer>> lists = Arrays.asList(list1, list2, list3);
        ZigZagIterator iterator = new ZigZagIterator(lists);

        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(1), iterator.next());
        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(10), iterator.next());
        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(100), iterator.next());
        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(2), iterator.next());
        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(200), iterator.next());
        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(300), iterator.next());
        Assert.assertEquals(false, iterator.hasNext());
    }

    @Test
    public void testOneList() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        List<List<Integer>> lists = Arrays.asList(list);
        ZigZagIterator iterator = new ZigZagIterator(lists);

        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(1), iterator.next());
        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(2), iterator.next());
        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(3), iterator.next());
        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(4), iterator.next());
        Assert.assertEquals(false, iterator.hasNext());
    }

    @Test
    public void testTwoListsWithDifferentSizes() {
        List<Integer> list1 = Arrays.asList(1);
        List<Integer> list2 = Arrays.asList(10, 20, 30);
        List<List<Integer>> lists = Arrays.asList(list1, list2);
        ZigZagIterator iterator = new ZigZagIterator(lists);

        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(1), iterator.next());
        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(10), iterator.next());
        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(20), iterator.next());
        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(30), iterator.next());
        Assert.assertEquals(false, iterator.hasNext());
    }

    @Test
    public void testTwoListsWithEmptyList() {
        List<Integer> list1 = Arrays.asList();
        List<Integer> list2 = Arrays.asList(10, 20, 30);
        List<List<Integer>> lists = Arrays.asList(list1, list2);
        ZigZagIterator iterator = new ZigZagIterator(lists);

        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(10), iterator.next());
        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(20), iterator.next());
        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(30), iterator.next());
        Assert.assertEquals(false, iterator.hasNext());
    }

    @Test
    public void testTwoListsWithSameSize() {
        List<Integer> list1 = Arrays.asList(1, 2);
        List<Integer> list2 = Arrays.asList(10, 20);
        List<List<Integer>> lists = Arrays.asList(list1, list2);
        ZigZagIterator iterator = new ZigZagIterator(lists);

        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(1), iterator.next());
        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(10), iterator.next());
        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(2), iterator.next());
        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(20), iterator.next());
        Assert.assertEquals(false, iterator.hasNext());
    }
}
