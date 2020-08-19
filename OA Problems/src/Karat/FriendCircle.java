package Karat;

import java.util.*;

public class FriendCircle {
    public static void main(String[] args) {
        FriendCircle test = new FriendCircle();
        String[] employees = {"1,Bill,Engineer",
                              "2,Joe,HR",
                              "3,Sally,Engineer",
                              "4,Richard,Business",
                              "6,Tom,Engineer",
                              "5,Donald,Sales"};
        String[] friendships = {"1,2",
                                "1,3",
                                "3,4",
                                "6,1",
                                "1,5"};
        System.out.println(test.findFriendRelations(employees, friendships));
        for (String s : test.friendInOtherDepartments(employees, friendships)) {
            System.out.println(s);
        }
        System.out.println(test.allInOneCircle(employees, friendships));
    }

    public Map<String, List<String>> findFriendRelations(String[] employees, String[] friendships) {
        Map<String, List<String>> relations = new HashMap<>();
        for (String employee : employees) {
            String[] person = employee.split(",");
            relations.put(person[0], new ArrayList<>());
        }
        for (String pair : friendships) {
            String[] friendRelation = pair.split(",");
            String id1 = friendRelation[0];
            String id2 = friendRelation[1];
            relations.get(id1).add(id2);
            relations.get(id2).add(id1);
        }
        return relations;
    }

    public List<String> friendInOtherDepartments(String[] employees, String[] friendships) {
        Map<String, Department> departmentNameToDepartment = new HashMap<>();
        Map<String, String> employeeToDepartment = new HashMap<>();
        for (String employee : employees) {
            String[] person = employee.split(",");
            Department d = departmentNameToDepartment.getOrDefault(person[2], new Department(person[2]));
            d.numEmployees++;
            departmentNameToDepartment.put(person[2], d);
            employeeToDepartment.put(person[0], person[2]);
        }
        for (String pair : friendships) {
            String[] friendRelation = pair.split(",");
            String id1 = friendRelation[0];
            String dName1 = employeeToDepartment.get(id1);
            String id2 = friendRelation[1];
            String dName2 = employeeToDepartment.get(id2);
            if (!dName1.equals(dName2)) {
                departmentNameToDepartment.get(dName1).hasOutsideFriends.add(id1);
                departmentNameToDepartment.get(dName2).hasOutsideFriends.add(id2);
            }
        }
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Department> entry : departmentNameToDepartment.entrySet()) {
            String name = entry.getValue().name;
            int numEmployees = entry.getValue().numEmployees;
            int numWhoHasOutsideFriends = entry.getValue().hasOutsideFriends.size();
            String output = name + ": " + numWhoHasOutsideFriends + " of " + numEmployees;
            res.add(output);
        }
        return res;
    }

    public boolean allInOneCircle(String[] employees, String[] friendships) {
        Map<String, List<String>> relations = findFriendRelations(employees, friendships);
        Set<String> covered = new HashSet<>();
        String firstId = friendships[0].split(",")[0];
        Queue<String> queue = new LinkedList<>();
        queue.offer(firstId);
        covered.add(firstId);
        while (!queue.isEmpty()) {
            String id = queue.poll();
            List<String> friendIds = relations.get(id);
            if (friendIds.size() == 0) {
                return false;
            }
            for (String friendId : friendIds) {
                if (covered.add(friendId)) {
                    queue.offer(friendId);
                }
            }
        }
        return covered.size() == relations.size();
    }


    static class Department {
        String name;
        int numEmployees;
        Set<String> hasOutsideFriends;

        public Department(String name) {
            this.name = name;
            this.numEmployees = 0;
            this.hasOutsideFriends = new HashSet<>();
        }
    }
}
