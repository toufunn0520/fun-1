package category.design.datastructure;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import category.design.datastructure.BasicMaxCounter;
import category.design.datastructure.MaxCounter;

public class BasicMaxCounterTest {

    private MaxCounter counter;

    @Before
    public void setUp() {
        counter = new BasicMaxCounter();
    }

    @Test
    public void testWithAllShowsUpOneTimeCases() {
        Assert.assertEquals(0, counter.getMaxCount());

        for (int i = 1; i <= 100; i++) {
            counter.add(i);
        }

        for (int i = 1; i <= 100; i++) {
            Assert.assertEquals(1, counter.getCount(i));
        }

        for (int i = 100; i > 0; i--) {
            Assert.assertEquals(1, counter.getMaxCount());
            counter.removeMaxCount();
        }
    }

    @Test
    public void testWithDecreasingTimeCases() {
        Assert.assertEquals(0, counter.getMaxCount());

        for (int i = 1; i <= 100; i++) {
            for (int j = 100 - i; j >= 0; j--) {
                counter.add(i);
            }
        }

        for (int i = 1; i <= 100; i++) {
            for (int j = 100 - i; j >= 0; j--) {
                Assert.assertEquals(101 - i, counter.getCount(i));
            }
        }

        for (int i = 1; i <= 100; i++) {
            Assert.assertEquals(101 - i, counter.getMaxCount());
            counter.removeMaxCount();
        }
    }

    @Test
    public void testWithIncreasingTimesCases() {
        Assert.assertEquals(0, counter.getMaxCount());

        for (int i = 1; i <= 100; i++) {
            for (int j = 0; j < 10 * i; j++) {
                counter.add(i);
            }
        }

        for (int i = 1; i <= 100; i++) {
            Assert.assertEquals(10 * i, counter.getCount(i));
        }

        for (int i = 100; i >= 0; i--) {
            Assert.assertEquals(i * 10, counter.getMaxCount());
            counter.removeMaxCount();
        }
    }

    @Test
    public void testWithMostTimesInMiddleCases() {
        Assert.assertEquals(0, counter.getMaxCount());

        for (int i = 1; i <= 100; i++) {
            counter.add(i);
        }

        for (int i = 1; i <= 100; i++) {
            counter.add(1000);
        }

        for (int i = 1; i <= 100; i++) {
            counter.add(i);
        }

        Assert.assertEquals(100, counter.getMaxCount());
        counter.removeMaxCount();
        Assert.assertEquals(2, counter.getMaxCount());
    }
}
