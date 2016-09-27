package category.design.datastructure;

import java.util.LinkedList;

/**
 * Dropbox onsite: Implement two methods called hit() and getHitCount(). The getHitCount would return how many hit()
 * method was called in last half minute.
 * 
 * @author boyi
 */
public class HitCounter {

    private int SIZE = 30;// SIZE is 60s/min * 0.5 min = 30 s.

    private LinkedList<Long> counters = new LinkedList<Long>();

    private long lastHitTimeInSeconds = 0;

    private int hitCount = 0;

    public void hit() {
        long curHitTimeInSeconds = System.currentTimeMillis() / 1000;

        long timeElapsedInSeconds = curHitTimeInSeconds - lastHitTimeInSeconds;
        if (timeElapsedInSeconds >= SIZE) {
            counters.clear();
            hitCount = 1;
            counters.add((long) 1);
        } else if (timeElapsedInSeconds == 0) {
            if (counters.size() == 0) {
                counters.add((long) 1);
            } else {
                int lastCounterIndex = counters.size() - 1;
                counters.set(lastCounterIndex, counters.get(lastCounterIndex) + 1);
            }
            hitCount++;
            return;
        } else {
            for (long i = 0; i < timeElapsedInSeconds - 1; i++) {
                counters.add((long) 0);
            }
            counters.add((long) 1);
            hitCount++;
        }

        while (counters.size() > SIZE) {
            hitCount -= counters.get(0);
            counters.remove(0);
        }

        lastHitTimeInSeconds = curHitTimeInSeconds;
    }

    public int getHitCount() {
        cleanExpiredCouter();
        return hitCount;
    }

    public void cleanExpiredCouter() {
        long curHitTimeInSeconds = System.currentTimeMillis() / 1000;

        long timeElapsedInSeconds = curHitTimeInSeconds - lastHitTimeInSeconds;
        if (timeElapsedInSeconds >= SIZE) {
            counters.clear();
            hitCount = 0;
        } else {
            for (int i = 0; i < timeElapsedInSeconds; i++) {
                counters.add((long) 0);
            }
            while (counters.size() > SIZE) {
                hitCount -= counters.get(0);
                counters.remove(0);
            }
        }

    }

}
