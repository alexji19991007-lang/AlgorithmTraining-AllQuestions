package Karat;

import java.util.*;

public class StudentCourses {
    public static void main(String[] args) {
        StudentCourses test = new StudentCourses();
        String[][] pairs = {{"58", "Software Design"}, {"58", "Linear Algebra"},
                {"94", "Art History"}, {"94", "Operating Systems"}, {"17", "Software Design"}, {"58", "Mechanics"},
                {"58", "Economics"}, {"17", "Linear Algebra"}, {"17", "Political Science"}, {"94", "Economics"},
                {"25", "Economics"}};
        for (String s : test.courseSchedule(pairs)) {
            System.out.println(s);
        }

        String[][] pairs2 = {{"a", "b"}, {"c", "d"}, {"b", "c"}, {"d", "e"}};
        System.out.println(test.midClass(pairs2));

        String[][] pairs3 = {{"Logic", "COBOL"}, {"Data Structures", "Algorithms"}, {"Creative Writing", "Data Structures"},
                {"Algorithms", "COBOL"}, {"Intro to Computer Science", "Data Structures"}, {"Logic", "Compilers"},
                {"Data Structures", "Logic"}, {"Creative Writing", "System Administration"}, {"Databases", "System Administration"},
                {"Creative Writing", "Databases"}, {"Intro to Computer Science", "Graphics"}};
        System.out.println(test.midClassInAPath(pairs3));
    }

    // TC: O(mn + mn^2)
    public List<String> courseSchedule(String[][] pairs) {
        List<String> res = new ArrayList<>();
        // Map each student to the course they are taking
        Map<String, Set<String>> studentCourses = new HashMap<>();
        List<String> studentList = new ArrayList<>();
        for (String[] pair : pairs) {
            String id = pair[0], course = pair[1];
            if (studentCourses.containsKey(id)) {
                studentCourses.get(id).add(course);
            } else {
                Set<String> courseSet = new HashSet<>();
                courseSet.add(course);
                studentCourses.put(id, courseSet);
                studentList.add(id);
            }
        }
        for (int i = 0; i < studentList.size(); ++i) {
            String id1 = studentList.get(i);
            Set<String> courses1 = studentCourses.get(id1);
            for (int j = i + 1; j < studentList.size(); ++j) {
                String id2 = studentList.get(j);
                Set<String> courses2 = studentCourses.get(id2);
                StringBuilder sb = new StringBuilder();
                sb.append("[").append(id1).append(", ").append(id2).append("]: [");
                for (String course : courses2) {
                    if (courses1.contains(course)) {
                        sb.append(course).append(", ");
                    }
                }
                if (sb.charAt(sb.length() - 1) == '[') {
                    sb.append("]");
                } else {
                    sb.replace(sb.length() - 2, sb.length(), "]");
                }
                res.add(sb.toString());
            }
        }
        return res;
    }

    public String midClass(String[][] pairs) {
        Map<String, Course> nameToCourse = new HashMap<>();
        for (String[] pair : pairs) {
            String pre = pair[0];
            Course preCourse = nameToCourse.getOrDefault(pre, new Course(pre));
            String after = pair[1];
            Course afterCourse = nameToCourse.getOrDefault(after, new Course(after));
            afterCourse.setPrerequisite(preCourse);
            nameToCourse.put(pre, preCourse);
            nameToCourse.put(after, afterCourse);
        }
        Course first = null;
        for (Map.Entry<String, Course> entry : nameToCourse.entrySet()) {
            if (entry.getValue().pre == null) {
                first = entry.getValue();
                break;
            }
        }
        int mid = (nameToCourse.size() - 1) / 2;
        Course midCourse = first;
        for (int i = 0; i < mid; ++i) {
            midCourse = midCourse.after;
        }
        return midCourse.name;
    }

    public Set<String> midClassInAPath(String[][] pairs) {
        Map<String, Course2> nameToCourse = new HashMap<>();
        Set<String> headNodes = new HashSet<>();
        // Make the graph & Find all head nodes
        for (String[] pair :pairs) {
            String pre = pair[0];
            Course2 preCourse = nameToCourse.getOrDefault(pre, new Course2(pre));
            String after = pair[1];
            Course2 afterCourse = nameToCourse.getOrDefault(after, new Course2(after));
            afterCourse.setPrerequisite(preCourse);
            // afterCourse can no longer be a potential head node
            headNodes.remove(after);
            // if the preCourse has no prerequisites, it is a potential head node
            if (preCourse.pre.size() == 0) {
                headNodes.add(pre);
            }
            nameToCourse.put(pre, preCourse);
            nameToCourse.put(after, afterCourse);
        }
        List<List<String>> allPaths = new ArrayList<>();
        for (String first : headNodes) {
            Course2 firstClass = nameToCourse.get(first);
            List<String> path = new ArrayList<>();
            path.add(first);
            findAllPaths(allPaths, firstClass, path);
        }
        Set<String> res = new HashSet<>();
        for (List<String> path : allPaths) {
            res.add(path.get((path.size() - 1) / 2));
        }
        return res;
    }

    private void findAllPaths(List<List<String>> allPaths, Course2 curClass, List<String> path) {
        if (curClass.after.size() == 0) {
            allPaths.add(new ArrayList<>(path));
            return;
        }
        for (Course2 next : curClass.after) {
            path.add(next.name);
            findAllPaths(allPaths, next, path);
            path.remove(path.size() - 1);
        }
    }

    static class Course {
        String name;
        Course pre;
        Course after;

        public Course(String name) {
            this.name = name;
        }

        public void setPrerequisite(Course prerequisite) {
            this.pre = prerequisite;
            prerequisite.after = this;
        }
    }

    static class Course2 {
        String name;
        Set<Course2> pre;
        Set<Course2> after;

        public Course2(String name) {
            this.name = name;
            this.pre = new HashSet<>();
            this.after = new HashSet<>();
        }

        public void setPrerequisite(Course2 prerequisite) {
            this.pre.add(prerequisite);
            prerequisite.after.add(this);
        }
    }
}