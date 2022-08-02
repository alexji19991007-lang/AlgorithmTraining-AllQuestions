package HackerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CalculatingMaximumExposureForATrader {
    public static void main(String[] args) {
        CalculatingMaximumExposureForATrader test = new CalculatingMaximumExposureForATrader();
        String d1 = "500 TSLA 195 845 1050";
        String d2 = "600 AAPL 153 855 930";
        List<String> data = Arrays.asList(d1, d2);
        System.out.println(test.calculate(2, data));
    }

    public int calculate(int n, List<String> data) {
        List<Action> actions = new ArrayList<>();
        for (String s : data) {
            String[] array = s.split(" ");
            int entryTime = Integer.parseInt(array[3]);
            int exitTime = Integer.parseInt(array[4]);
            int price = Integer.parseInt(array[2]);
            int amount = Integer.parseInt(array[0]);
            String symbol = array[1];
            actions.add(new Action(entryTime, symbol, true, amount, price));
            actions.add(new Action(exitTime, symbol, false, amount, price));
        }
        actions.sort(Comparator.comparingInt(a -> a.time));
//        List<Interval> intervals = new ArrayList<>();
//        int lastStart = -1;
        int curCapital = 0;
        int maxCapital = 0;
        for (Action action : actions) {
            if (action.isBuy) {
//                if (lastStart != -1) {
//                    intervals.add(new Interval(lastStart, action.time, curCapital));
//                }
                curCapital += action.amount * action.price;
                maxCapital = Math.max(maxCapital, curCapital);
            } else {
//                intervals.add(new Interval(lastStart,
//                action.time, curCapital));
                curCapital -= action.amount * action.price;
            }
//            lastStart = action.time;
        }
//        System.out.println(intervals);
        return maxCapital;
    }

    static class Action {
        int time;
        String symbol;
        boolean isBuy;
        int amount;
        int price;

        public Action(int time, String symbol, boolean isBuy, int amount, int price) {
            this.time = time;
            this.symbol = symbol;
            this.isBuy = isBuy;
            this.amount = amount;
            this.price = price;
        }
    }

    static class Interval {
        int start;
        int end;
        int capital;

        public Interval(int start, int end, int capital) {
            this.start = start;
            this.end = end;
            this.capital = capital;
        }

        public String toString() {
            return "(" + start + ", " + end + "): " + capital;
        }
    }
}
