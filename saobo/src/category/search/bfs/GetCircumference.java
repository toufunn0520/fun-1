package category.search.bfs;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Google onsite: Given a matrix, each block in the matrix is one kind of color. Now give you a start block, find all
 * continuous blocks with the same color and get the circumference. You can suppose each block is 1*1, so the
 * circumference of a block is 4.
 * 
 * @author boyi
 */
public class GetCircumference {

    public static void main(String[] args) {
        Color[][] matrix = { { Color.Yellow, Color.Green, Color.Yellow, Color.Yellow, Color.Green },
                { Color.Red, Color.Red, Color.Red, Color.Red, Color.Green },
                { Color.Yellow, Color.Yellow, Color.Red, Color.Green, Color.Yellow },
                { Color.Green, Color.Yellow, Color.Red, Color.Red, Color.Yellow } };
        Point startPoint = new Point(1, 1);

        System.out.println(new GetCircumference(matrix, startPoint).getCircumference());

    }

    public GetCircumference(Color[][] matrix, Point startPoint) {
        if (matrix == null)
            throw new RuntimeException("input matrix cannot be null.");

        this.xLen = matrix[0].length;
        this.yLen = matrix.length;

        if (startPoint.x >= this.xLen || startPoint.y >= this.yLen)
            throw new RuntimeException("input start point index is not valid.");

        this.matrix = matrix;
        this.visited = new int[yLen][xLen];
        this.startPoint = startPoint;
        this.color = matrix[startPoint.y][startPoint.x];
    }

    private final Point startPoint;

    private final int yLen;

    private final int xLen;

    private final Color[][] matrix;

    private final int[][] visited;

    private final Color color;

    public int getCircumference() {
        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(startPoint);

        int circum = 0;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            visited[point.y][point.x] = 1;
            circum += getIncreasedCircum(point.x, point.y);
            if (point.x + 1 < xLen && matrix[point.y][point.x + 1] == color && visited[point.y][point.x + 1] != 1) {
                queue.offer(new Point(point.x + 1, point.y));
            }

            if (point.y + 1 < yLen && matrix[point.y + 1][point.x] == color && visited[point.y + 1][point.x] != 1) {
                queue.offer(new Point(point.x, point.y + 1));
            }

            if (point.x - 1 >= 0 && matrix[point.y][point.x - 1] == color && visited[point.y][point.x - 1] != 1) {
                queue.offer(new Point(point.x - 1, point.y));
            }

            if (point.y - 1 >= 0 && matrix[point.y - 1][point.x] == color && visited[point.y - 1][point.x] != 1) {
                queue.offer(new Point(point.x, point.y - 1));
            }
        }

        return circum;
    }

    private int getIncreasedCircum(int x, int y) {

        int increasedCircum = 4;
        if (x + 1 < xLen && visited[y][x + 1] == 1) {
            increasedCircum -= 2;
        }
        if (x - 1 >= 0 && visited[y][x - 1] == 1) {
            increasedCircum -= 2;
        }
        if (y + 1 < yLen && visited[y + 1][x] == 1) {
            increasedCircum -= 2;
        }

        if (y - 1 >= 0 && visited[y - 1][x] == 1) {
            increasedCircum -= 2;
        }

        return increasedCircum;
    }

    enum Color {
        Red, Yellow, Green
    }
}
