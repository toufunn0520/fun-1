package category.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * POJ 1456 A supermarket has a set of Products to sell. Given the profit and due day of each product. Suppose you can
 * only sell one per day and can only sell the product before the due day. What is the max profits. Analysis: Greedy +
 * Union find.
 * 
 * @author boyi
 */
public class SuperMarket {

    public static void main(String[] args) {

        Product product1 = new Product(50, 2);
        Product product2 = new Product(10, 1);
        Product product3 = new Product(20, 2);
        Product product4 = new Product(30, 1);

        Product[] products = new Product[4];
        products[0] = product1;
        products[1] = product2;
        products[2] = product3;
        products[3] = product4;

        SuperMarket market = new SuperMarket(products);
        System.out.println(market.getMaxProfits());

    }

    private Product[] products;

    private int[] availableDay;

    public static final Comparator<Product> PRODUCT_COMPARATOR = new Comparator<Product>() {

        @Override
        public int compare(Product o1, Product o2) {
            if (o1.profit < o2.profit) {
                return 1;
            } else if (o1.profit > o2.profit) {
                return -1;
            } else {
                return 0;
            }
        }

    };

    public SuperMarket(Product[] products) {
        this.products = products;
        availableDay = new int[products.length];

        for (int i = 0; i < products.length; i++) {
            availableDay[i] = i;
        }
    }

    public int getMaxProfits() {
        Arrays.sort(products, PRODUCT_COMPARATOR);
        int maxProfits = 0;
        for (int i = 0; i < products.length; i++) {
            int t = find(products[i].dueDay);
            if (t > 0) {
                maxProfits += products[i].profit;
                availableDay[t] = t - 1;
            }
        }

        return maxProfits;
    }

    private int find(int x) {
        if (availableDay[x] == x) {
            return x;
        }

        availableDay[x] = find(availableDay[x]);

        return availableDay[x];
    }

}

class Product {

    public int profit;

    public int dueDay;

    public Product(int profit, int dueDay) {
        this.profit = profit;
        this.dueDay = dueDay;
    }
}
