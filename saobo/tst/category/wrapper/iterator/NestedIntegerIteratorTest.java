package category.wrapper.iterator;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import category.container.stack.BaseNestedInteger;
import category.container.stack.NestedIntegerIterator;

public class NestedIntegerIteratorTest {

    /**
     * <1,2, < <3,4>, 5, < 6, <7> > >, 8>
     */
    @Test
    public void testWithMultipleLayersNestedList() {
        BaseNestedInteger first = new BaseNestedInteger(1);
        BaseNestedInteger second = new BaseNestedInteger(2);

        BaseNestedInteger third_one_one = new BaseNestedInteger(3);
        BaseNestedInteger third_one_two = new BaseNestedInteger(4);
        BaseNestedInteger third_one = new BaseNestedInteger(Arrays.asList(third_one_one, third_one_two));

        BaseNestedInteger third_two = new BaseNestedInteger(5);

        BaseNestedInteger third_three_one = new BaseNestedInteger(6);
        BaseNestedInteger third_three_two = new BaseNestedInteger(Arrays.asList(new BaseNestedInteger(7)));
        BaseNestedInteger third_three = new BaseNestedInteger(Arrays.asList(third_three_one, third_three_two));

        BaseNestedInteger third = new BaseNestedInteger(Arrays.asList(third_one, third_two, third_three));
        BaseNestedInteger forth = new BaseNestedInteger(8);

        BaseNestedInteger root = new BaseNestedInteger(Arrays.asList(first, second, third, forth));

        NestedIntegerIterator it = new NestedIntegerIterator(root);

        for (int i = 0; i < 8; i++) {
            Assert.assertTrue(it.hasNext());
            Assert.assertSame(i + 1, it.next());
        }
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testWithOneInteger() {
        BaseNestedInteger i = new BaseNestedInteger(1);
        NestedIntegerIterator it = new NestedIntegerIterator(i);

        Assert.assertTrue(it.hasNext());
        Assert.assertSame(1, it.next());
        Assert.assertFalse(it.hasNext());
    }

    /**
     * <0, <1,2,3,4,5,6,7,8,9,10>, 11>
     */
    @Test
    public void testWithOneLayerNestedList() {
        BaseNestedInteger[] integerList = new BaseNestedInteger[10];
        for (int i = 0; i < integerList.length; i++) {
            integerList[i] = new BaseNestedInteger(i + 1);
        }

        BaseNestedInteger integerWithList = new BaseNestedInteger(Arrays.asList(integerList));

        BaseNestedInteger integer1 = new BaseNestedInteger(0);
        BaseNestedInteger integer11 = new BaseNestedInteger(11);

        NestedIntegerIterator it = new NestedIntegerIterator(new BaseNestedInteger(Arrays.asList(integer1,
                integerWithList, integer11)));

        for (int i = 0; i < 12; i++) {
            Assert.assertTrue(it.hasNext());
            Assert.assertSame(i, it.next());
        }
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testWithOneList() {
        BaseNestedInteger[] integers = new BaseNestedInteger[10];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = new BaseNestedInteger(i);
        }

        BaseNestedInteger rootInteger = new BaseNestedInteger(Arrays.asList(integers));
        NestedIntegerIterator it = new NestedIntegerIterator(rootInteger);

        for (int i = 0; i < 10; i++) {
            Assert.assertTrue(it.hasNext());
            Assert.assertSame(i, it.next());
        }
        Assert.assertFalse(it.hasNext());
    }

}
