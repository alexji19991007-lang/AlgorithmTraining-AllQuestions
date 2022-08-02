import java.util.*;

public class ArbitrageProfit {
    public static void main(String[] args) {
        ArbitrageProfit test = new ArbitrageProfit();
        List<String> l1 = Arrays.asList("USD", "EUR", "0.741");
        List<String> l2 = Arrays.asList("EUR", "USD", "1.349");
        List<String> l3 = Arrays.asList("USD", "CAD", "1.005");
        List<String> l4 = Arrays.asList("CAD", "USD", "0.995");
        List<String> l5 = Arrays.asList("EUR", "CAD", "1.366");
        List<String> l6 = Arrays.asList("CAD", "EUR", "0.732");
        List<String> l7 = Arrays.asList("USD", "GBP", "0.657");
        List<String> l8 = Arrays.asList("GBP", "USD", "1.521");
        List<String> l9 = Arrays.asList("USD", "CHF", "1.061");
        List<String> l10 = Arrays.asList("CHF", "USD", "0.942");
        List<String> l11 = Arrays.asList("EUR", "CHF", "1.433");
        List<String> l12 = Arrays.asList("CHF", "EUR", "0.698");
        List<String> l13 = Arrays.asList("EUR", "GBP", "0.888");
        List<String> l14 = Arrays.asList("GBP", "EUR", "0.126");
        List<String> l15 = Arrays.asList("CAD", "CHF", "1.049");
        List<String> l16 = Arrays.asList("CHF", "CAD", "0.953");
        List<String> l17 = Arrays.asList("CAD", "GBP", "0.650");
        List<String> l18 = Arrays.asList("GBP", "CAD", "1.538");
        List<String> l19 = Arrays.asList("GBP", "CHF", "1.614");
        List<String> l20 = Arrays.asList("CHF", "GBP", "0.619");
        List<List<String>> currencyList = new ArrayList<>();
        currencyList.add(l1);
        currencyList.add(l2);
        currencyList.add(l3);
        currencyList.add(l4);
        currencyList.add(l5);
        currencyList.add(l6);
        currencyList.add(l7);
        currencyList.add(l8);
        currencyList.add(l9);
        currencyList.add(l10);
        currencyList.add(l11);
        currencyList.add(l12);
        currencyList.add(l13);
        currencyList.add(l14);
        currencyList.add(l15);
        currencyList.add(l16);
        currencyList.add(l17);
        currencyList.add(l18);
        currencyList.add(l19);
        currencyList.add(l20);
        System.out.println(test.calculateMaxProfit(currencyList, "USD"));
    }

    public double calculateMaxProfit(List<List<String>> currencyList, String s) {
        Map<String, Currency> currencyMap = new HashMap<>();
        buildGraph(currencyMap, currencyList);
        Currency start = currencyMap.get(s);
        Set<String> visited = new HashSet<>();
        double[] maxProfit = new double[] {1.0};
        DFS(start, visited, 1, maxProfit, start);
        return maxProfit[0];
    }

    public void DFS(Currency cur, Set<String> visited, double curProfit, double[] maxProfit, Currency start) {
        for (Map.Entry<Currency, Double> neighbor : cur.exchangeRate.entrySet()) {
            Currency next = neighbor.getKey();
            if (!visited.contains(next.symbol)) {
                double newProfit = curProfit * neighbor.getValue();
                if (next.symbol.equals(start.symbol)) {
                    maxProfit[0] = Math.max(maxProfit[0], newProfit);
                    continue;
                }
                visited.add(next.symbol);
                DFS(next, visited, newProfit, maxProfit, start);
                visited.remove(next.symbol);
            }
        }
    }

    public void buildGraph(Map<String, Currency> currencyMap, List<List<String>> currencyList) {
        for (List<String> list : currencyList) {
            String from = list.get(0);
            String to = list.get(1);
            double rate = Double.parseDouble(list.get(2));
            Currency fromCurrency = currencyMap.getOrDefault(from, new Currency(from));
            Currency toCurrency = currencyMap.getOrDefault(to, new Currency(to));
            fromCurrency.addRate(toCurrency, rate);
            currencyMap.put(from, fromCurrency);
            currencyMap.put(to, toCurrency);
        }
    }

    static class Currency {
        String symbol;
        Map<Currency, Double> exchangeRate;

        public Currency(String symbol) {
            this.symbol = symbol;
            this.exchangeRate = new HashMap<>();
        }

        public void addRate(Currency c, double rate) {
            exchangeRate.put(c, rate);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Currency)) {
                return false;
            }
            Currency c = (Currency) o;
            return c.symbol.equals(this.symbol);
        }

        @Override
        public int hashCode() {
            return symbol.hashCode();
        }
    }
}
