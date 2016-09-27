package category.graph.unionfind;

/**
 * uva 1160 X-Plosives There are serveral Products. Each product is a mix of two different compounds. If N different
 * products containing N different compounds, it would create a explosive. Now given bunch of products, you should
 * determine if the product can be loaded with the other products. For example, if given A+B, B+C, you can load them
 * together, when the third is C+A, you should refuse since it would create explosive. Input: given a sequential
 * products, each is two numbers indicating two different compounds. Output: the number of refusals to prevent an
 * explosive. Analysis: union find. If the two compounds have the same root in the union find graph, it would create an
 * explosive.
 * 
 * @author boyi
 */
public class XPlosive {

    public static void main(String[] args) {
        XPlosive xPlosive = new XPlosive(7);
        xPlosive.addProduct(1, 2);
        xPlosive.addProduct(3, 4);
        xPlosive.addProduct(3, 5);
        xPlosive.addProduct(3, 1);
        xPlosive.addProduct(2, 3);
        xPlosive.addProduct(4, 1);
        xPlosive.addProduct(2, 6);
        xPlosive.addProduct(6, 5);

        System.out.println(xPlosive.getNumberOfRefusals());
    }

    private int[] father;

    private int numberOfRefusals;

    public XPlosive(int numberOfCompounds) {
        if (numberOfCompounds < 1)
            throw new IllegalArgumentException("number of compounds cannot be less than 1");
        father = new int[numberOfCompounds];
        this.numberOfRefusals = 0;
        for (int i = 0; i < numberOfCompounds; i++) {
            father[i] = i;
        }
    }

    public Boolean addProduct(int xIndex, int yIndex) {
        if (xIndex < 0 || yIndex < 0) {
            throw new IllegalArgumentException();
        }

        int xFather = findFather(xIndex);
        int yFather = findFather(yIndex);

        if (xFather == yFather) {
            numberOfRefusals++;
            return false;
        } else {
            father[xFather] = yFather;
            return true;
        }
    }

    private int findFather(int index) {
        if (father[index] != index) {
            father[index] = father[father[index]];
        }

        return father[index];
    }

    public int getNumberOfRefusals() {
        return this.numberOfRefusals;
    }

}
