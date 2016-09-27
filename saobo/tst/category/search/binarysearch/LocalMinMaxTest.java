package category.search.binarysearch;

import org.junit.Assert;
import org.junit.Test;

import category.search.binarysearch.LocalMinMax.MinMaxType;

public class LocalMinMaxTest {

    @Test
    public void TestMonotonouscalIncreasing() {
        int[] input = { 1, 2, 3, 4 };
        Assert.assertEquals(0, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Max).size());
        Assert.assertEquals(0, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Min).size());
    }

    @Test
    public void TestMonotonouscalDecreasing() {
        int[] input = { 4, 2, 0 };
        Assert.assertEquals(0, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Max).size());
        Assert.assertEquals(0, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Min).size());
    }

    @Test
    public void TestArrayWithSameValue() {
        int[] input = { 8, 8, 8 };
        Assert.assertEquals(0, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Max).size());
        Assert.assertEquals(0, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Min).size());
    }

    @Test
    public void TestArraySizeLessThanThree() {
        int[] input1 = null;
        int[] input2 = {};
        int[] input3 = { 8 };
        int[] input4 = { 8, 8 };
        Assert.assertEquals(null, LocalMinMax.findLocalMinMax(input1));
        Assert.assertEquals(null, LocalMinMax.findLocalMinMax(input2));
        Assert.assertEquals(null, LocalMinMax.findLocalMinMax(input3));
        Assert.assertEquals(null, LocalMinMax.findLocalMinMax(input4));
    }

    @Test
    public void TestArrayWithLocalMin() {
        int[] input = { 8, 2, 8 };
        Assert.assertEquals(0, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Max).size());
        Assert.assertEquals(1, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Min).size());
        Assert.assertEquals((Integer) 1, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Min).get(0));
    }

    @Test
    public void TestArrayWithLocalMax() {
        int[] input = { 8, 10, 9 };
        Assert.assertEquals(0, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Min).size());
        Assert.assertEquals(1, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Max).size());
        Assert.assertEquals((Integer) 1, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Max).get(0));
    }

    @Test
    public void TestArrayWithLocalMaxMin() {
        int[] input = { 8, 10, 9, 10 };
        Assert.assertEquals(1, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Min).size());
        Assert.assertEquals((Integer) 2, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Min).get(0));
        Assert.assertEquals(1, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Max).size());
        Assert.assertEquals((Integer) 1, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Max).get(0));
    }

    @Test
    public void TestArrayWithMultipleLocalMaxMin() {
        int[] input = { 9, 10, 9, 10, 9, 10 };
        Assert.assertEquals(2, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Min).size());
        Assert.assertEquals((Integer) 2, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Min).get(0));
        Assert.assertEquals((Integer) 4, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Min).get(1));
        Assert.assertEquals(2, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Max).size());
        Assert.assertEquals((Integer) 1, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Max).get(0));
        Assert.assertEquals((Integer) 3, LocalMinMax.findLocalMinMax(input).get(MinMaxType.Max).get(1));
    }
}
