package Karat;

import java.util.*;

public class TaskByLevel {
    public static void main(String[] args) {
        TaskByLevel test = new TaskByLevel();
        String[][] pairs = {{"cook", "eat"}, {"study", "eat"}, {"sleep", "study"}, {"play", "cook"}, {"play", "study"}};
        System.out.println(test.taskByLevel(pairs).toString());
    }

    public List<List<String>> taskByLevel(String[][] pairs) {
        Map<String, Action> nameToAction = new HashMap<>();
        Set<Action> hasPre = new HashSet<>();
        for (String[] pair : pairs) {
            Action before = nameToAction.getOrDefault(pair[0], new Action(pair[0]));
            Action after = nameToAction.getOrDefault(pair[1], new Action(pair[1]));
            after.setPre(before);
            hasPre.remove(after);
            if (before.pre.isEmpty()) {
                hasPre.add(before);
            }
            nameToAction.put(pair[0], before);
            nameToAction.put(pair[1], after);
        }
        List<List<String>> res = new ArrayList<>();
        Queue<Action> allPreFinished = new LinkedList<>(hasPre);
        while (!allPreFinished.isEmpty()) {
            int size = allPreFinished.size();
            List<String> thisRound = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                Action cur = allPreFinished.poll();
                thisRound.add(cur.action);
                for (Action next : cur.after) {
                    next.pre.remove(cur);
                    if (next.pre.size() == 0) {
                        allPreFinished.offer(next);
                    }
                }
            }
            res.add(thisRound);
        }
        return res;
    }


    static class Action {
        String action;
        Set<Action> pre;
        Set<Action> after;

        public Action(String action) {
            this.action = action;
            this.pre = new HashSet<>();
            this.after = new HashSet<>();
        }

        public void setPre(Action before) {
            this.pre.add(before);
            before.after.add(this);
        }
    }
}
