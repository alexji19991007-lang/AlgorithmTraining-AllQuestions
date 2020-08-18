package Karat;

import java.util.*;

public class BadgeAccess {
    public static void main(String[] args) {
        BadgeAccess test = new BadgeAccess();
        String[][] records = {{"Martha", "exit"}, {"Paul", "enter"}, {"Martha", "enter"},
                {"Martha", "exit"}, {"Jennifer", "enter"}, {"Paul", "enter"}, {"Curtis", "enter"},
                {"Paul", "exit"}, {"Martha", "enter"}, {"Martha", "exit"}, {"Jennifer", "exit"}};
        String[][] accessRecords = {{"Paul", "1355"}, {"Jennifer", "1910"}, {"John", "830"}, {"Paul", "1315"},
                {"John", "835"}, {"Paul", "1405"}, {"Paul", "1630"}, {"John", "855"}, {"John", "915"},
                {"John", "930"}, {"Jennifer", "1335"}, {"Jennifer", "730"}, {"John", "1630"}};
        System.out.println(test.onlyEntryOrExit(records));
        System.out.println(test.suspiciousRecord(accessRecords));
    }

    public List<List<String>> onlyEntryOrExit(String[][] records) {
        Map<String, Person> nameToPerson = new HashMap<>();
        for (String[] record : records) {
            String name = record[0], action = record[1];
            Person p = nameToPerson.getOrDefault(name, new Person(name));
            if (action.equals("enter")) {
                p.entered++;
            } else {
                p.exited++;
            }
            nameToPerson.put(name, p);
        }
        List<List<String>> res = new ArrayList<>();
        List<String> noExit = new ArrayList<>();
        List<String> noEnter = new ArrayList<>();
        for (Map.Entry<String, Person> entry : nameToPerson.entrySet()) {
            Person p = entry.getValue();
            if (p.entered != p.exited) {
                if (p.entered > p.exited) {
                    noExit.add(p.name);
                } else {
                    noEnter.add(p.name);
                }
            }
        }
        res.add(noExit);
        res.add(noEnter);
        return res;
    }

    public Map<String, List<String>> suspiciousRecord(String[][] records) {
        Arrays.sort(records, (r1, r2) -> {
            int time1 = Integer.parseInt(r1[1]);
            int time2 = Integer.parseInt(r2[1]);
            return time1 - time2;
        });
        Map<String, Employee> nameToEmployee = new HashMap<>();
        Map<String, List<String>> res = new HashMap<>();
        for (String[] record : records) {
            String name = record[0];
            Time time = new Time(record[1]);
            Employee e = nameToEmployee.getOrDefault(name, new Employee(name));
            while (!e.accessRecord.isEmpty() && !withinOneHour(e.accessRecord.peek(), time)) {
                e.accessRecord.poll();
            }
            e.accessRecord.offer(time);
            nameToEmployee.put(name , e);
            if (e.accessRecord.size() >= 3) {
                List<String> timeRecord = res.getOrDefault(name, new ArrayList<>());
                if (!timeRecord.isEmpty()) {
                    if (timeRecord.get(0).equals(e.accessRecord.peek().strTime)) {
                        timeRecord.add(time.strTime);
                    }
                } else {
                    for (Time t : e.accessRecord) {
                        timeRecord.add(t.strTime);
                    }
                }
                res.put(name, timeRecord);
            }
        }
        return res;
    }

    private boolean withinOneHour(Time t1, Time t2) {
        return t2.time - t1.time <= 60;
    }

    static class Person {
        String name;
        int entered;
        int exited;

        public Person(String name) {
            this.name = name;
            this.entered = 0;
            this.exited = 0;
        }
    }

    static class Employee {
        String name;
        Queue<Time> accessRecord;

        public Employee(String name) {
            this.name = name;
            this.accessRecord = new LinkedList<>();
        }
    }

    static class Time {
        String strTime;
        int time;

        public Time(String t) {
            this.strTime = t;
            int hour = Integer.parseInt(t.substring(0, t.length() - 2));
            int minute = Integer.parseInt(t.substring(t.length() - 2));
            this.time = hour * 24 + minute;
        }
    }
}
