package category.search.dfs;

import interview.utils.Point;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FlowingWater {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    /**
     * <pre>
     * 输入是一个 N*N的矩阵，代表地势高度。如果下雨水流只能流去比他矮或者一样高的地势。
     * 矩阵左边和上边是太平洋，右边和下边是大西洋。求出所有的能同时流到两个大洋的点。
     * 
     * For example:
     * Pacific: ~
     * Atlantic: *
     * ~  ~   ~   ~   ~   ~  ~
     * ~  1   2   2   3  (5) *
     * ~  3   2   3  (4) (4) *
     * ~  2   4  (5)  3   1  *
     * ~ (6) (7)  1   4   5  *
     * ~ (5)  1   1   2   4  *
     *    *   *   *   *   *  *
     * 括号里即为结果：
     * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]
     * </pre>
     *
     * @param mat
     * @return
     */
    public List<Point> flowing_water(int[][] mat) {
        int n = mat.length;

        Set<Point> visited_pacific = new HashSet<Point>();
        for (int i = 0; i < n; ++i) {
            Point p = new Point(0, i);
            visited_pacific.add(p);
            search(p, visited_pacific, mat);
        }
        for (int i = 0; i < n; ++i) {
            Point p = new Point(i, 0);
            visited_pacific.add(p);
            search(p, visited_pacific, mat);
        }

        Set<Point> visited_atlantic = new HashSet<Point>();
        for (int i = 0; i < n; ++i) {
            Point p = new Point(n - 1, i);
            visited_atlantic.add(p);
            search(p, visited_atlantic, mat);
        }

        for (int i = 0; i < n; ++i) {
            Point p = new Point(i, n - 1);
            visited_atlantic.add(p);
            search(p, visited_atlantic, mat);
        }
        List<Point> ret = new ArrayList<Point>();
        Iterator<Point> atlanticIterator = visited_atlantic.iterator();
        while (atlanticIterator.hasNext()) {
            Point key = atlanticIterator.next();
            if (visited_pacific.contains(key)) {
                ret.add(key);
            }
        }
        return ret;
    }

    void search(Point pt, Set<Point> visited, int[][] matrix) {
        int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        for (int i = 0; i < 4; ++i) {
            int[] direction = directions[i];
            Point newPosition = new Point(direction[0] + pt.x, direction[1] + pt.y);
            if (newPosition.x < 0 || newPosition.x >= matrix.length || newPosition.y < 0
                    || newPosition.y >= matrix.length) {
                continue;
            }
            if (matrix[newPosition.x][newPosition.y] < matrix[pt.x][pt.y] || visited.contains(newPosition)) {
                continue;
            }
            visited.add(newPosition);
            search(newPosition, visited, matrix);
        }
    }

}
