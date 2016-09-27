package interview.utils;

public class Point {

    final public int x;

    final public int y;

    public Point(int y, int x) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) {
            return false;
        }

        return this.x == ((Point) o).x && this.y == ((Point) o).y;
    }

    @Override
    public int hashCode() {
        return (x + "" + y).hashCode();
    }

    @Override
    public String toString() {
        return "y:" + y + " x:" + x;
    }
}
