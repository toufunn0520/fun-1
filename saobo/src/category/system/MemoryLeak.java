package category.system;

import java.util.HashSet;
import java.util.Set;

public class MemoryLeak {

    public static void main(String[] args) {
        System.gc();
        System.out.println(Runtime.getRuntime().freeMemory());
        Set<DemoClass> set = new HashSet<DemoClass>();

        DemoClass[] ds = new DemoClass[100000];
        for (int i = 0; i < 100000; i++) {
            ds[i] = new DemoClass("demo" + i, i);
            set.add(ds[i]);
        }

        System.out.println(Runtime.getRuntime().freeMemory());

        for (int i = 0; i < 100000; i++) {
            ds[i] = null;
        }

        // ds = null;
        set = null;

        System.gc();
        System.out.println(Runtime.getRuntime().freeMemory());
    }

}
