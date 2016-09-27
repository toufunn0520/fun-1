package category.design.datastructure;

import org.junit.Assert;
import org.junit.Test;

import category.design.datastructure.HitCounter;

public class HitCounterTest {

    @Test
    public void zeroCount() {
        HitCounter hitCounter = new HitCounter();
        Assert.assertEquals(hitCounter.getHitCount(), 0);
    }

    @Test
    public void oneCount() {
        HitCounter hitCounter = new HitCounter();
        hitCounter.hit();
        Assert.assertEquals(hitCounter.getHitCount(), 1);
    }

    @Test
    public void clearCountsAfterHalfMinutes() throws InterruptedException {
        HitCounter hitCounter = new HitCounter();
        hitCounter.hit();
        Assert.assertEquals(hitCounter.getHitCount(), 1);
        Thread.sleep(30 * 1000);
        Assert.assertEquals(hitCounter.getHitCount(), 0);
    }

    @Test
    public void hitInOneSecond() {
        HitCounter hitCounter = new HitCounter();
        int hitNumbers = 300;
        for (int i = 0; i < hitNumbers; i++) {
            hitCounter.hit();
        }
        Assert.assertEquals(hitCounter.getHitCount(), hitNumbers);
    }

    @Test
    public void hitInTwoSeconds() throws InterruptedException {
        HitCounter hitCounter = new HitCounter();
        int hitNumbers = 300;
        for (int i = 0; i < hitNumbers; i++) {
            hitCounter.hit();
        }
        Thread.sleep(2000);
        for (int i = 0; i < hitNumbers; i++) {
            hitCounter.hit();
        }
        Assert.assertEquals(hitCounter.getHitCount(), 2 * hitNumbers);
    }

    @Test
    public void hitEverySecond() throws InterruptedException {
        HitCounter hitCounter = new HitCounter();
        int hitNumbers = 30;
        for (int i = 0; i < hitNumbers; i++) {
            Thread.sleep(1000);
            hitCounter.hit();
        }
        Assert.assertEquals(hitCounter.getHitCount(), hitNumbers);
    }

    @Test
    public void hitEverySecondsMoreThanHalfMin() throws InterruptedException {
        HitCounter hitCounter = new HitCounter();
        int hitNumbers = 50;
        for (int i = 0; i < hitNumbers; i++) {
            Thread.sleep(1000);
            hitCounter.hit();
        }
        Assert.assertEquals(hitCounter.getHitCount(), 30);
    }
}
