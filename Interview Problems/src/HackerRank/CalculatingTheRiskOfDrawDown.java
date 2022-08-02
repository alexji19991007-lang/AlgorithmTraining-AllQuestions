package HackerRank;

import java.util.ArrayList;
import java.util.List;

public class CalculatingTheRiskOfDrawDown {
    public static void main(String[] args) {
        CalculatingTheRiskOfDrawDown test = new CalculatingTheRiskOfDrawDown();
        String line = "0.22 -0.05 0.17 -0.03 0.12 -0.02 0.41 0.22 -0.03";
        test.calculateDrawDown1(line);
        //test.calculateDrawDown(line);
    }

    public void calculateDrawDown(String returnRecordLine) {
        String[] array = returnRecordLine.split(" ");
        List<Double> returns = new ArrayList<>();
        for (String s : array) {
            returns.add(Double.parseDouble(s));
        }
        List<List<Double>> allPermutations = new ArrayList<>();
        findPermutations(returns, 0, allPermutations);
        int numPossibilities = allPermutations.size();
        int lossCount = 0;
        for (List<Double> list : allPermutations) {
            double cumulative = 10000.0, highMark = 10000.0;
            for (double d : list) {
                cumulative *= (1 + d);
                highMark = Math.max(highMark, cumulative);
                double drawDown = (cumulative - highMark) / highMark;
                if (drawDown < -0.1) {
                    lossCount++;
                    break;
                }
            }
        }
        System.out.printf("%.4f %n", (double) lossCount / numPossibilities);
    }

    public void findPermutations(List<Double> returns, int index, List<List<Double>> allPermutations) {
        if (index == returns.size()) {
            allPermutations.add(new ArrayList<>(returns));
            return;
        }
        for (int i = index; i < returns.size(); ++i) {
            swap(returns, i, index);
            findPermutations(returns, index + 1, allPermutations);
            swap(returns, i, index);
        }
    }

    public void swap(List<Double> returns, int i, int j) {
        double temp = returns.get(i);
        returns.set(i, returns.get(j));
        returns.set(j, temp);
    }

    public void calculateDrawDown1(String returnRecordLine) {
        String[] array = returnRecordLine.split(" ");
        List<Double> returns = new ArrayList<>();
        for (String s : array) {
            returns.add(Double.parseDouble(s));
        }
        int numPossibilities = factorial(returns.size());
        int[] lossCount = new int[] {0};
        findPermutations1(lossCount, returns, 0, 10000.0, 10000.0);
        System.out.printf("%.4f %n", (double) lossCount[0] / numPossibilities);
    }

    public void findPermutations1(int[] lossCount, List<Double> returns, int index, double cumulative, double highMark) {
        if (index == returns.size()) {
            return;
        }
        for (int i = index; i < returns.size(); ++i) {
            swap(returns, i, index);
            double prevCum = cumulative;
            double prevHighMark = highMark;
            cumulative *= (1 + returns.get(index));
            highMark = Math.max(highMark, cumulative);
            double drawDown = (cumulative - highMark) / highMark;
            if (drawDown < -0.1) {
                lossCount[0] += factorial(returns.size() - index - 1);
            } else {
                findPermutations1(lossCount, returns, index + 1, cumulative, highMark);
            }
            swap(returns, i, index);
            highMark = prevHighMark;
            cumulative = prevCum;
        }
    }

    public int factorial(int num) {
        int res = 1;
        for (int i = 1; i <= num; ++i) {
            res *= i;
        }
        return res;
    }
}
