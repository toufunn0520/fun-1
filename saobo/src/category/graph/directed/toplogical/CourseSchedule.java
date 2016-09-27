package category.graph.directed.toplogical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class CourseSchedule {

    /**
     * [Leetcode 207] https://leetcode.com/problems/course-schedule/
     *
     * <pre>
     * There are a total of n courses you have to take, labeled from 0 to n - 1.
     * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed
     * as a pair: [0,1]. Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish
     * all courses?
     *
     * For example:
     * 2, [[1,0]]
     * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
     *
     * 2, [[1,0],[0,1]]
     * There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you
     * should also have finished course 1. So it is impossible.
     * </pre>
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        // ith in the list: i should be before all the nodes in the value list.
        List<List<Integer>> prerequsitelist = new ArrayList<List<Integer>>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            prerequsitelist.add(new ArrayList<Integer>());
        }

        for (int[] prerequisite : prerequisites) {
            prerequsitelist.get(prerequisite[0]).add(prerequisite[1]);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] hasScheduled = new boolean[numCourses];
        Arrays.fill(visited, false);
        Arrays.fill(hasScheduled, false);

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && !canFinishHelper(prerequsitelist, visited, hasScheduled, i)) {
                return false;
            }
        }

        return true;
    }

    private boolean canFinishHelper(List<List<Integer>> prerequsitelist, boolean[] visited, boolean[] hasScheduled,
            int current) {
        visited[current] = true;
        hasScheduled[current] = true;

        for (int prerequisite : prerequsitelist.get(current)) {
            if (hasScheduled[prerequisite]) {
                return false;
            }
            if (!visited[prerequisite] && !canFinishHelper(prerequsitelist, visited, hasScheduled, prerequisite)) {
                return false;
            }
        }

        hasScheduled[current] = false;
        return true;
    }

    /**
     * [Leetcode 210] https://leetcode.com/problems/course-schedule-ii/
     *
     * <pre>
     * There are a total of n courses you have to take, labeled from 0 to n - 1. Some courses may have prerequisites, for example
     * to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
     * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to
     * finish all courses. There may be multiple correct orders, you just need to return one of them. If it is impossible to
     * finish all courses, return an empty array.
     *
     * For example:
     *
     * 2, [[1,0]]
     * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]
     *
     * 4, [[1,0],[2,0],[3,1],[3,2]]
     * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2
     * should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
     * </pre>
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            int[] natualOrder = new int[numCourses];

            for (int i = 0; i < numCourses; i++) {
                natualOrder[i] = i;
            }
            return natualOrder;
        }

        List<List<Integer>> prerequsitelist = new ArrayList<List<Integer>>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            prerequsitelist.add(new ArrayList<Integer>());
        }

        for (int[] prerequisite : prerequisites) {
            prerequsitelist.get(prerequisite[1]).add(prerequisite[0]);
        }

        Stack<Integer> orderedCourse = new Stack<Integer>();
        boolean[] visited = new boolean[numCourses];
        boolean[] hasScheduled = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (!findOrderDFS(prerequsitelist, visited, hasScheduled, orderedCourse, i)) {
                    return new int[0];
                }
            }
        }

        int i = 0;
        int[] result = new int[numCourses];
        while (!orderedCourse.isEmpty()) {
            result[i++] = orderedCourse.pop();
        }

        return result;
    }

    public boolean findOrderDFS(List<List<Integer>> prerequisites, boolean[] visited, boolean[] hasScheduled,
            Stack<Integer> stack, int current) {
        visited[current] = true;
        hasScheduled[current] = true;

        for (int prerequisite : prerequisites.get(current)) {
            if (hasScheduled[prerequisite]) {
                return false;
            }

            if (!visited[prerequisite] && !findOrderDFS(prerequisites, visited, hasScheduled, stack, prerequisite)) {
                return false;
            }
        }

        hasScheduled[current] = false;
        stack.push(current);
        return true;
    }
}
