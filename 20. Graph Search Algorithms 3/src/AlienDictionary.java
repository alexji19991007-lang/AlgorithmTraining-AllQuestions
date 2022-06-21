import java.util.*;

public class AlienDictionary {
    public static void main(String[] args) {
        String[] words = {"wwww", "wrt", "wrf", "er", "ett", "fftt"};
        AlienDictionary test = new AlienDictionary();
        System.out.println(test.alienOrder(words));
    }

    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return null;
        }
        // Suppose there is an entry like: 'c' --> <'x', 'j'>, it basically means that there are two letters 'x' and 'j'
        // having higher priority than letter 'c'.
        Map<Character, Set<Character>> graph = new HashMap<>();
        buildGraph(words, graph);
        return topologicalSort(graph);
    }

    public String topologicalSort(Map<Character, Set<Character>> graph) {
        Queue<Character> queue = new ArrayDeque<>();
        StringBuilder s = new StringBuilder();
        // Initialize the queue. If a node contains no incoming edges, then put it into the queue.
        for (Map.Entry<Character, Set<Character>> entry : graph.entrySet()) {
            if (entry.getValue().size() == 0) {
                queue.offer(entry.getKey());
            }
        }
        while (!queue.isEmpty()) {
            char out = queue.poll();
            s.append(out);
            for (Map.Entry<Character, Set<Character>> entry : graph.entrySet()) {
                if (entry.getValue().remove(out)) {
                    if (entry.getValue().size() == 0) {
                        queue.offer(entry.getKey());
                    }
                }
            }
        }
        return s.length() == graph.size() ? s.toString() : "";
    }

    public void buildGraph(String[] words, Map<Character, Set<Character>> graph) {
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
            }
        }
        for (int i = 0; i < words.length - 1; ++i) {
            String s1 = words[i];
            String s2 = words[i + 1];
            for (int k = 0; k < Math.min(s1.length(), s2.length()); ++k) {
                char c1 = s1.charAt(k);
                char c2 = s2.charAt(k);
                if (c1 != c2) {
                    // If two characters don't equal, that means c1 has a higher priority than c2
                    // We make an edge from c2 --> c1 (from low priority --> high priority).
                    graph.get(c2).add(c1);
                    break;
                }
            }
        }
    }
}
