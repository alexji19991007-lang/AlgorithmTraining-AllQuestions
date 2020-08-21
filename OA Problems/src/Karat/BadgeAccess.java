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
        String[][] badgeRecords = {{"Paul", "1214", "enter"}, {"Paul", "830", "enter"},
                {"Curtis", "1100", "enter"}, {"Paul", "903", "exit"}, {"John", "908", "exit"},
                {"Paul", "1235", "exit"}, {"Jennifer", "900", "exit"}, {"Curtis", "1330", "exit"},
                {"John", "815", "enter"}, {"Jennifer", "1217", "enter"}, {"Curtis", "745", "enter"},
                {"John", "1230", "enter"}, {"Jennifer", "800", "enter"}, {"John", "1235", "exit"},
                {"Curtis", "810", "exit"}, {"Jennifer", "1240", "exit"}};
        System.out.println(test.onlyEntryOrExit(records));
        System.out.println(test.suspiciousRecord(accessRecords));
        System.out.println();
        System.out.println(test.groupEntry(badgeRecords));
    }

    // *********************************************************************************************
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

    // *********************************************************************************************
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
            nameToEmployee.put(name, e);
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

    // *********************************************************************************************
    public String groupEntry(String[][] records) {
        // Find all people's names
        Set<String> names = new HashSet<>();
        for (String[] record : records) {
            names.add(record[0]);
        }

        // Find all subsets of people with size >= 2
        // Map each group to their number of occurrences
        Map<Set<String>, List<TimeInterval>> groupOccurrence = new HashMap<>();
        findAllSubSets(new ArrayList<>(names), 0, new HashSet<>(), groupOccurrence);

        // Sort the record according to time
        Arrays.sort(records, Comparator.comparingInt(r -> Integer.parseInt(r[1])));
        RoomCondition curCond = new RoomCondition();
        for (String[] record : records) {
            String name = record[0], time = record[1], action = record[2];
            curCond.to = time;
            if (curCond.numPeople > 1) {
                for (Set<String> group : groupOccurrence.keySet()) {
                    if (containsAll(curCond.people, group)) {
                        groupOccurrence.get(group).add(new TimeInterval(curCond.from, curCond.to));
                    }
                }
            }
            if (action.equals("enter")) {
                curCond.people.add(name);
                curCond.numPeople++;
            } else {
                curCond.people.remove(name);
                curCond.numPeople--;
            }
            curCond.from = time;
        }

        // Merge each key's corresponding list of TimeIntervals
        mergeIntervals(groupOccurrence);
        Set<String> largestSet = new HashSet<>();
        List<TimeInterval> interval = new ArrayList<>();
        for (Map.Entry<Set<String>, List<TimeInterval>> entry : groupOccurrence.entrySet()) {
            if (entry.getValue().size() <= 1) {
                continue;
            }
            if (entry.getKey().size() > largestSet.size()) {
                largestSet = entry.getKey();
                interval = entry.getValue();
            }
        }

        // Build the result String
        StringBuilder res = new StringBuilder();
        res.append(largestSet.toString()).append(": ");
        for (TimeInterval t : interval) {
            res.append(t.from).append(" to ").append(t.to).append(", ");
        }
        res.delete(res.length() - 2, res.length());
        return res.toString();
    }

    private void findAllSubSets(List<String> nameList, int index, Set<String> solution, Map<Set<String>, List<TimeInterval>> groupOccurrence) {
        if (index == nameList.size()) {
            Set<String> group = new HashSet<>(solution);
            if (group.size() > 1) {
                groupOccurrence.put(group, new ArrayList<>());
            }
            return;
        }
        solution.add(nameList.get(index));
        findAllSubSets(nameList, index + 1, solution, groupOccurrence);
        solution.remove(nameList.get(index));
        findAllSubSets(nameList, index + 1, solution, groupOccurrence);
    }

    private boolean containsAll(Set<String> big, Set<String> small) {
        if (big.size() < small.size()) {
            return false;
        }
        for (String smallElements : small) {
            if (!big.contains(smallElements)) {
                return false;
            }
        }
        return true;
    }

    private void mergeIntervals(Map<Set<String>, List<TimeInterval>> map) {
        for (Set<String> key : map.keySet()) {
            List<TimeInterval> intervals = map.get(key);
            if (intervals.size() == 0) {
                continue;
            }
            List<TimeInterval> mergedIntervals = new ArrayList<>();
            TimeInterval newInterval = intervals.get(0);
            mergedIntervals.add(newInterval);
            for (TimeInterval interval : intervals) {
                if (Integer.parseInt(interval.from) > Integer.parseInt(newInterval.to)) {
                    newInterval = interval;
                    mergedIntervals.add(newInterval);
                } else {
                    int newTo = Math.max(Integer.parseInt(newInterval.to), Integer.parseInt(interval.to));
                    newInterval.to = "" + newTo;
                }
            }
            map.put(key, mergedIntervals);
        }
    }

    // *********************************************************************************************
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

    // *********************************************************************************************
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

    // *********************************************************************************************
    static class RoomCondition {
        Set<String> people;
        int numPeople;
        String from;
        String to;

        public RoomCondition() {
            this.people = new HashSet<>();
            this.numPeople = 0;
            this.from = "";
            this.to = "";
        }
    }

    static class TimeInterval {
        String from;
        String to;

        public TimeInterval(String from, String to) {
            this.from = from;
            this.to = to;
        }
    }
}
