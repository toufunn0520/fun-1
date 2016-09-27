package category.container.heap;

import interview.utils.Point;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class FindNearestPoints {

    private class PointComparator implements Comparator<Point> {

        private Point mPoint;

        public PointComparator(Point mPoint) {
            this.mPoint = mPoint;
        }

        @Override
        public int compare(Point o1, Point o2) {
            long distance1 = (o1.y - mPoint.y) * (o1.y - mPoint.y) + (o1.x - mPoint.x) * (o1.x - mPoint.x);
            long distance2 = (o2.y - mPoint.y) * (o2.y - mPoint.y) + (o2.x - mPoint.x) * (o2.x - mPoint.x);

            return Long.compare(distance2, distance1);
        }

    }

    /**
     * [Microsoft OA] Find the closest point to the reference point from a set of points.
     * 
     * @param allPoints
     * @param referencePoint
     * @param k
     * @return
     */
    public Set<Point> FindClosestPoints(Set<Point> allPoints, Point referencePoint, int k) {

        PointComparator comp = new PointComparator(referencePoint);
        PriorityQueue<Point> queue = new PriorityQueue<Point>(k, comp);

        for (Point point : allPoints) {
            if (queue.size() < k) {
                queue.add(point);
            } else {
                Point top = queue.peek();

                if (comp.compare(top, point) < 0) {
                    queue.poll();
                    queue.add(point);
                }
            }
        }

        return new HashSet<Point>(queue);
    }
}
