import java.util.*;

// LeetCode 399
public class EvaluateDivision {
    // TC: O(N) to make graph + O(M * N) to traverse the entire graph which takes O(N) M times = O(M * N)
    // SC: O(N)
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Dividend> graph = constructGraph(equations, values);
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); ++i) {
            List<String> query = queries.get(i);
            String dividend = query.get(0), divisor = query.get(1);
            if (!graph.containsKey(dividend) || !graph.containsKey(divisor)) {
                res[i] = -1.0;
            } else if (dividend == divisor) {
                res[i] = 1.0;
            } else {
                Set<String> visited = new HashSet<>();
                res[i] = backtrack(graph, dividend, divisor, 1, visited);
            }
        }
        return res;
    }

    private Map<String, Dividend> constructGraph(List<List<String>> equations, double[] values) {
        Map<String, Dividend> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); ++i) {
            List<String> equation = equations.get(i);
            String dividend = equation.get(0), divisor = equation.get(1);
            double quotient = values[i];
            graph.putIfAbsent(dividend, new Dividend(dividend));
            graph.get(dividend).divisorToQuotient.put(divisor, quotient);
            graph.putIfAbsent(divisor, new Dividend(divisor));
            graph.get(divisor).divisorToQuotient.put(dividend, 1 / quotient);
        }
        return graph;
    }

    private double backtrack(Map<String, Dividend> graph, String cur, String target, double curProduct, Set<String> visited) {
        visited.add(cur);
        double res = -1.0;
        Map<String, Double> neighbors = graph.get(cur).divisorToQuotient;
        if (neighbors.containsKey(target)) {
            res = curProduct * neighbors.get(target);
        } else {
            for (Map.Entry<String, Double> entry : neighbors.entrySet()) {
                String next = entry.getKey();
                if (visited.contains(next)) {
                    continue;
                }
                res = backtrack(graph, next, target, curProduct * entry.getValue(), visited);
                if (res != -1.0) {
                    return res;
                }
            }
        }
        visited.remove(cur);
        return res;
    }

    static class Dividend {
        String s;
        Map<String, Double> divisorToQuotient;

        public Dividend(String s) {
            this.s = s;
            this.divisorToQuotient = new HashMap<>();
        }
    }
}
