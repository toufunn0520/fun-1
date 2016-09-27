package category.graph.unionfind;

/**
 * hdu 1213 How Many Tables Ignatius want to invite friends to the party so needs to know how many tables he needs at
 * the party. One important rule for this problem is that if I tell you A knows B, and B knows C, that means A, B, C
 * know each other, so they can stay in one table. For example: If I tell you A knows B, B knows C, and D knows E, so A,
 * B, C can stay in one table, and D, E have to stay in the other one. So Ignatius needs 2 tables at least.
 * 
 * @author boyi
 */
public class HowManyTables {

    public static void main(String[] args) {

        WeightedQUWithPathCompression uf = new WeightedQUWithPathCompression(5);
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(2, 3);

        System.out.println(uf.getCount());
    }
}

class WeightedQUWithPathCompression {

    // how many components
    private int count;

    // stores the parent
    private int[] id;

    // stores the size of the subtree
    private int[] size;

    public WeightedQUWithPathCompression(int N) {
        this.count = N;
        this.id = new int[N];
        this.size = new int[N];

        for (int i = 0; i < this.count; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    private int findRoot(int p) {
        while (p != id[p]) {
            id[p] = id[id[p]]; // compress the path to root
            p = id[p];
        }

        return p;
    }

    public void union(int p, int q) {
        int pRoot = this.findRoot(p);
        int qRoot = this.findRoot(q);

        if (pRoot == qRoot) {
            // p q already unioned
            return;
        }

        // union by size of the substree.
        if (size[pRoot] > size[qRoot]) {
            id[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        } else {
            id[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        }

        // number of components would decrease after union
        count--;
    }

    public int getCount() {
        return this.count;
    }

}
