package category.container.stack;

import java.util.List;

public class BaseNestedInteger implements NestedInteger {

    private Integer integer;

    private List<NestedInteger> list;

    public BaseNestedInteger(int i) {
        this.integer = i;
    }

    public BaseNestedInteger(List<NestedInteger> list) {
        if (list == null) {
            throw new RuntimeException();
        }

        this.list = list;
    }

    @Override
    public Integer getInteger() {
        return integer;
    }

    @Override
    public List<NestedInteger> getList() {
        return list;
    }

    @Override
    public boolean isInteger() {
        return integer != null;
    }

    @Override
    public String toString() {
        return "integer: " + integer + " list: " + list;
    }

}
