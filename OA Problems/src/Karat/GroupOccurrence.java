package Karat;

import java.util.*;
import java.util.concurrent.TimeoutException;

public class GroupOccurrence {
    public static void main(String[] args) {
        GroupOccurrence test = new GroupOccurrence();
        String[][] badgeRecords = {{"Paul", "1214", "enter"}, {"Paul", "830", "enter"},
                {"Curtis", "1100", "enter"}, {"Paul", "903", "exit"}, {"John", "908", "exit"},
                {"Paul", "1235", "exit"}, {"Jennifer", "900", "exit"}, {"Curtis", "1330", "exit"},
                {"John", "815", "enter"}, {"Jennifer", "1217", "enter"}, {"Curtis", "745", "enter"},
                {"John", "1230", "enter"}, {"Jennifer", "800", "enter"}, {"John", "1235", "exit"},
                {"Curtis", "810", "exit"}, {"Jennifer", "1240", "exit"}};
        System.out.println(test.groupEntry(badgeRecords));
    }

    public String groupEntry(String[][] records) {
        // Find all people's names
        Map<String, Integer> nameToId = new HashMap<>();
        Map<Integer, String> idToName = new HashMap<>();
        // id ranges from 0 to n - 1;
        int n = 0;
        for (String[] record : records) {
            if (!nameToId.containsKey(record[0])) {
                nameToId.put(record[0], n);
                idToName.put(n, record[0]);
                n++;
            }
        }
        // Sort the record according to time
        Arrays.sort(records, Comparator.comparingInt(r -> Integer.parseInt(r[1])));
        Map<Set<Integer>, List<TimeInterval>> groupOccurrence = new HashMap<>();
        RoomCondition curCond = new RoomCondition();
        for (String[] record : records) {
            String name = record[0], time = record[1], action = record[2];
            curCond.to = time;
            if (curCond.numPeople > 1) {
                Set<Integer> group = new HashSet<>(curCond.people);
                List<TimeInterval> intervals = groupOccurrence.getOrDefault(group, new ArrayList<>());
                intervals.add(new TimeInterval(curCond.from, curCond.to));
                groupOccurrence.put(group, intervals);
            }
            if (action.equals("enter")) {
                curCond.people.add(nameToId.get(name));
                curCond.numPeople++;
            } else {
                curCond.people.remove(nameToId.get(name));
                curCond.numPeople--;
            }
            curCond.from = time;
        }
        for (Map.Entry<Set<Integer>, List<TimeInterval>> group1 : groupOccurrence.entrySet()) {
            for (Map.Entry<Set<Integer>, List<TimeInterval>> group2 : groupOccurrence.entrySet()) {
                if (group1.equals(group2)) {
                    continue;
                }
                if (containsAll(group1.getKey(), group2.getKey())) {
                    for (TimeInterval t : group1.getValue()) {
                        group2.getValue().add(new TimeInterval(t.from, t.to));
                    }
                }
            }
        }
        for (Map.Entry<Set<Integer>, List<TimeInterval>> group : groupOccurrence.entrySet()) {
            group.getValue().sort(Comparator.comparingInt(g -> Integer.parseInt(g.from)));
        }

        mergeIntervals(groupOccurrence);
        Set<Integer> largestSet = new HashSet<>();
        List<TimeInterval> interval = new ArrayList<>();
        for (Map.Entry<Set<Integer>, List<TimeInterval>> entry : groupOccurrence.entrySet()) {
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
        for (Integer id : largestSet) {
            res.append(idToName.get(id)).append(", ");
        }
        res.delete(res.length() - 2, res.length());
        res.append(": ");
        for (TimeInterval t : interval) {
            res.append(t.from).append(" to ").append(t.to).append(", ");
        }
        res.delete(res.length() - 2, res.length());
        return res.toString();
    }

    private void mergeIntervals(Map<Set<Integer>, List<TimeInterval>> map) {
        for (Set<Integer> key : map.keySet()) {
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

    private boolean containsAll(Set<Integer> big, Set<Integer> small) {
        if (big.size() < small.size()) {
            return false;
        }
        for (int smallElements : small) {
            if (!big.contains(smallElements)) {
                return false;
            }
        }
        return true;
    }

    static class RoomCondition {
        Set<Integer> people;
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
