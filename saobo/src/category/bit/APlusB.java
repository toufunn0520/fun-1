package category.bit;

/**
 * Write a function that add two numbers A and B. You should not use + or any arithmetic operators.
 * 
 * @author boyi
 */
public class APlusB {

    public static void main(String[] args) {
        System.out.println(APlusB.aplusb(2, 2));

    }

    public static int aplusb(int a, int b) {
        if (b == 0) {
            return a;
        }

        int sum = a ^ b;
        int carry = (a & b) << 1;
        return aplusb(sum, carry);
    }

}
