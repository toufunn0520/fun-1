package category.bit;

public class RepresentInt {

    public static void main(String[] args) {
        new RepresentInt().printBinary(14);

        System.out.println(1 >> 0);

    }

    public void printBinary(int n) {
        for (int i = 0; i < 32; i++, n <<= 1) {
            if (n < 0) {
                System.out.print(1);
            } else {
                System.out.print(0);
            }
        }
    }

}
