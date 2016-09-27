package category.design.datastructure.simple;

import java.io.Serializable;

public final class SingletonDemo implements Serializable {

    private static class FooLoader {

        private static final SingletonDemo INSTANCE = new SingletonDemo();
    }

    private static final long serialVersionUID = 1L;

    public static SingletonDemo getInstance() {
        return FooLoader.INSTANCE;
    }

    private SingletonDemo() {
        if (FooLoader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    @SuppressWarnings("unused")
    private SingletonDemo readResolve() {
        return FooLoader.INSTANCE;
    }
}
