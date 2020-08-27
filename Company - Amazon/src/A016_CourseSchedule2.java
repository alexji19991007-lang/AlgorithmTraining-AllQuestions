import java.util.*;

// LeetCode 210
public class A016_CourseSchedule2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Map the course number to the corresponding course object
        Map<Integer, Course> numToCourse = new HashMap<>();
        for (int i = 0; i < numCourses; ++i) {
            numToCourse.put(i, new Course(i));
        }
        for (int[] dependency : prerequisites) {
            // pre is the prerequisite of next
            int after = dependency[0];
            Course afterCourse = numToCourse.get(after);
            int pre = dependency[1];
            Course preCourse = numToCourse.get(pre);
            afterCourse.setPre(preCourse);
        }
        return topologicalSort(numToCourse);
    }

    public int[] topologicalSort(Map<Integer, Course> numToCourse) {
        int numCourses = numToCourse.size();
        int[] topologicalOrder = new int[numCourses];
        Queue<Course> queue = new LinkedList<>();
        for (Map.Entry<Integer, Course> entry : numToCourse.entrySet()) {
            // if no incoming edges
            if (entry.getValue().pre.size() == 0) {
                queue.offer(entry.getValue());
            }
        }
        int numExpanded = 0;
        while (!queue.isEmpty()) {
            Course cur = queue.poll();
            topologicalOrder[numExpanded++] = cur.courseNum;
            for (Course afterCourse : cur.after) {
                afterCourse.pre.remove(cur);
                if (afterCourse.pre.size() == 0) {
                    queue.add(afterCourse);
                }
            }
        }
        return numExpanded == numCourses ? topologicalOrder : new int[]{};
    }

    static class Course {
        int courseNum;
        Set<Course> pre;
        Set<Course> after;

        public Course(int courseNum) {
            this.courseNum = courseNum;
            this.pre = new HashSet<>();
            this.after = new HashSet<>();
        }

        public void setPre(Course preCourse) {
            this.pre.add(preCourse);
            preCourse.after.add(this);
        }
    }
}
