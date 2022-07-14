package HackerRank;

import java.util.*;

public class PrimeFactorVisitation {
    public static void main(String[] args) {
        PrimeFactorVisitation test = new PrimeFactorVisitation();
        //System.out.println(test.getPrimeFactors(779));
        System.out.println(Arrays.toString(test.flip(new int[]{1, 1, 0, 0, 1, 1, 0, 1, 1, 1}, new int[]{3, 4, 15})));
    }

    public int[] flip(int[] states, int[] numbers) {
        Map<Integer, Integer> allPrimeFactors = new HashMap<>();
        for (int n : numbers) {
            getPrimeFactors(n, allPrimeFactors);
        }
        for (Map.Entry<Integer, Integer> entry : allPrimeFactors.entrySet()) {
            if (entry.getValue() == 1) {
                int factor = entry.getKey();
                for (int i = factor - 1; i < states.length; i += factor) {
                    states[i] = 1 - states[i];
                }
            }
        }
        return states;
    }

    public void getPrimeFactors(int n, Map<Integer, Integer> primeFactors) {
        if (n % 2 == 0) {
            primeFactors.put(2, 1 - primeFactors.getOrDefault(2, 0));
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                primeFactors.put(i, 1 - primeFactors.getOrDefault(i, 0));
            }
            while (n % i == 0) {
                n /= i;
            }
        }
        if (n > 2) {
            primeFactors.put(n, 1 - primeFactors.getOrDefault(n, 0));
        }
    }
}
