package category.design.datastructure;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import category.design.datastructure.MaxStack;
import category.design.datastructure.ProMaxStack;

public class ProMaxStackTest {

    private MaxStack maxStack;

    @Before
    public void setUp() {
        maxStack = new ProMaxStack();
    }

    @Test
    public void testWithBaseCases() {
        maxStack.push(1);
        maxStack.push(10);
        maxStack.push(2);
        maxStack.push(8);

        Assert.assertEquals(10, maxStack.peekMax());
        Assert.assertEquals(8, maxStack.peek());
        Assert.assertEquals(10, maxStack.popMax());
        Assert.assertEquals(8, maxStack.peek());
        Assert.assertEquals(8, maxStack.popMax());
        Assert.assertEquals(2, maxStack.peek());
        Assert.assertEquals(2, maxStack.popMax());
        Assert.assertEquals(1, maxStack.peek());
        Assert.assertEquals(1, maxStack.popMax());
    }

    @Test
    public void testWithBasePopPushCases() {
        maxStack.push(1);
        maxStack.push(10);
        maxStack.push(2);
        maxStack.push(8);

        Assert.assertEquals(8, maxStack.peek());
        Assert.assertEquals(8, maxStack.pop());
        Assert.assertEquals(2, maxStack.peek());
        Assert.assertEquals(2, maxStack.pop());
        Assert.assertEquals(10, maxStack.peek());
        Assert.assertEquals(10, maxStack.pop());
        Assert.assertEquals(1, maxStack.peek());
        Assert.assertEquals(1, maxStack.pop());
    }

    @Test
    public void testWithDuplicatesCases() {
        maxStack.push(1);
        maxStack.push(10);
        maxStack.push(2);
        maxStack.push(10);
        maxStack.push(8);

        Assert.assertEquals(10, maxStack.popMax());
        Assert.assertEquals(8, maxStack.pop());
        Assert.assertEquals(2, maxStack.pop());
        Assert.assertEquals(10, maxStack.pop());
        Assert.assertEquals(1, maxStack.pop());
    }
}
