package category.greedy;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * POJ 2287 田忌赛马. Input: two arrays of integers indicate how good the horses are. Output: What is the theoretically best
 * result for group1. Note: The more games win and less games lose is the best result.
 * 
 * @author boyi
 */
public class HorseRace {

    public static void main(String[] args) {
        int[] hourseGroup1 = { 90, 80, 70, 100, 58 };
        int[] hourseGroup2 = { 90, 80, 70, 102, 23 };

        Result result = new HorseRace().getBestResult(hourseGroup1, hourseGroup2);
        System.out.println(result.winCount);
        System.out.println(result.loseCount);
        System.out.println(result.tieCount);

    }

    /**
     * 策略：先比最好的马，如果能赢则直接比赛，不能赢的话再比较最差的马，如果能赢则直接比赛，不能赢的话将最差的马去比最好的马。
     * 
     * @param horseGroup1
     * @param horseGroup2
     * @return
     */
    public Result getBestResult(int[] horseGroup1, int[] horseGroup2) {
        Arrays.sort(horseGroup1);
        Arrays.sort(horseGroup2);

        int beginIndex1 = 0;
        int beginIndex2 = 0;
        int endIndex1 = horseGroup1.length - 1;
        int endIndex2 = endIndex1;

        int winCount = 0;
        int loseCount = 0;
        int tieCount = 0;

        while (beginIndex1 <= endIndex1) {
            if (horseGroup1[endIndex1] > horseGroup2[endIndex2]) {
                winCount++;
                endIndex1--;
                endIndex2--;
            } else if (horseGroup1[beginIndex1] > horseGroup2[beginIndex2]) {
                winCount++;
                beginIndex1++;
                beginIndex2++;
            } else {
                if (horseGroup1[beginIndex1] > horseGroup2[endIndex2]) {
                    winCount++;
                } else if (horseGroup1[beginIndex1] < horseGroup2[endIndex2]) {
                    loseCount++;
                } else {
                    tieCount++;
                }
                beginIndex1++;
                endIndex2--;
            }
        }

        return new Result(winCount, loseCount, tieCount);
    }

    @Test
    public void testAllWin() {
        int[] testhourseGroup1 = { 90, 80, 70 };
        int[] testhourseGroup2 = { 88, 78, 68 };

        Result result = new HorseRace().getBestResult(testhourseGroup1, testhourseGroup2);
        Assert.assertEquals(3, result.winCount);
        Assert.assertEquals(0, result.loseCount);
        Assert.assertEquals(0, result.tieCount);
    }

    @Test
    public void testTwoWin() {
        int[] testhourseGroup1 = { 88, 78, 68 };
        int[] testhourseGroup2 = { 90, 80, 70 };

        Result result = new HorseRace().getBestResult(testhourseGroup1, testhourseGroup2);
        Assert.assertEquals(2, result.winCount);
        Assert.assertEquals(0, result.tieCount);
        Assert.assertEquals(1, result.loseCount);
    }

    @Test
    public void testTwoWinOneLose() {
        int[] testhourseGroup1 = { 88, 78, 70 };
        int[] testhourseGroup2 = { 90, 80, 70 };

        Result result = new HorseRace().getBestResult(testhourseGroup1, testhourseGroup2);
        Assert.assertEquals(2, result.winCount);
        Assert.assertEquals(0, result.tieCount);
        Assert.assertEquals(1, result.loseCount);
    }

    @Test
    public void testTwoWinOneTie() {
        int[] testhourseGroup1 = { 91, 81, 70 };
        int[] testhourseGroup2 = { 90, 80, 70 };

        Result result = new HorseRace().getBestResult(testhourseGroup1, testhourseGroup2);
        Assert.assertEquals(2, result.winCount);
        Assert.assertEquals(1, result.tieCount);
        Assert.assertEquals(0, result.loseCount);
    }

    @Test
    public void testTwoWinOneLose2() {
        int[] testhourseGroup1 = { 90, 75, 70 };
        int[] testhourseGroup2 = { 90, 80, 70 };

        Result result = new HorseRace().getBestResult(testhourseGroup1, testhourseGroup2);
        Assert.assertEquals(2, result.winCount);
        Assert.assertEquals(0, result.tieCount);
        Assert.assertEquals(1, result.loseCount);
    }

    @Test
    public void testTwoWinOneLose3() {
        int[] testhourseGroup1 = { 90, 80, 70 };
        int[] testhourseGroup2 = { 90, 80, 70 };

        Result result = new HorseRace().getBestResult(testhourseGroup1, testhourseGroup2);
        Assert.assertEquals(2, result.winCount);
        Assert.assertEquals(0, result.tieCount);
        Assert.assertEquals(1, result.loseCount);
    }

    @Test
    public void testTwoWinOneLose4() {
        int[] testhourseGroup1 = { 90, 80, 73 };
        int[] testhourseGroup2 = { 90, 80, 70 };

        Result result = new HorseRace().getBestResult(testhourseGroup1, testhourseGroup2);
        Assert.assertEquals(2, result.winCount);
        Assert.assertEquals(0, result.tieCount);
        Assert.assertEquals(1, result.loseCount);
    }

    @Test
    public void testOneWinTwoLose() {
        int[] testhourseGroup1 = { 90, 75, 68 };
        int[] testhourseGroup2 = { 90, 80, 78 };

        Result result = new HorseRace().getBestResult(testhourseGroup1, testhourseGroup2);
        Assert.assertEquals(1, result.winCount);
        Assert.assertEquals(0, result.tieCount);
        Assert.assertEquals(2, result.loseCount);
    }
}

class Result {

    public int winCount;

    public int loseCount;

    public int tieCount;

    public Result(int winCount, int loseCount, int tieCount) {
        this.winCount = winCount;
        this.loseCount = loseCount;
        this.tieCount = tieCount;
    }
}
