import java.util.*;

public class AlienDictionary {
    public static void main(String[] args) {
        String[] words = {"wwww", "wrt", "wrf", "er", "ett", "fftt"};
        String[] words2 = {"z", "x", "z"};
        AlienDictionary test = new AlienDictionary();
        System.out.println(test.alienOrder(words));
    }

    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Set<Character> charSet = getCharSet(words);
        buildGraph(words, graph);
        return topologicalSort(graph, charSet);
    }

    public String topologicalSort(Map<Character, Set<Character>> graph, Set<Character> charSet) {
        int numChars = charSet.size();
        char[] topologicalOrder = new char[numChars];
        Map<Character, Integer> incomingEdges = new HashMap<>();
        for (char x : charSet) {
            incomingEdges.put(x, 0);
        }
        for (char x : charSet) {
            Set<Character> next = graph.getOrDefault(x, null);
            if (next == null) {
                continue;
            }
            for (char y : next) {
                incomingEdges.put(y, incomingEdges.get(y) + 1);
            }
        }
        Queue<Character> q = new ArrayDeque<>();
        for (char x : incomingEdges.keySet()) {
            if (incomingEdges.get(x) == 0) {
                q.offer(x);
            }
        }
        int numExpanded = 0;
        while (!q.isEmpty()) {
            char cur = q.poll();
            topologicalOrder[numExpanded++] = cur;
            if (graph.get(cur) == null) {
                continue;
            }
            for (char y : graph.get(cur)) {
                int newIncomingEdgeCount = incomingEdges.get(y) - 1;
                incomingEdges.put(y, newIncomingEdgeCount);
                if (newIncomingEdgeCount == 0) {
                    q.offer(y);
                }
            }
        }
        return numExpanded == numChars ? new String(topologicalOrder) : "";
    }

    public void buildGraph(String[] words, Map<Character, Set<Character>> graph) {
        for (int i = 0; i < words.length - 1; ++i) {
            String s1 = words[i];
            String s2 = words[i + 1];
            for (int k = 0; k < Math.min(s1.length(), s2.length()); ++k) {
                char c1 = s1.charAt(k);
                char c2 = s2.charAt(k);
                if (c1 != c2) {
                    Set<Character> adjList = graph.getOrDefault(c1, new HashSet<>());
                    if (!adjList.contains(c2)) {
                        adjList.add(c2);
                    }
                    graph.put(c1, adjList);
                    break;
                }
            }
        }
    }

    public Set<Character> getCharSet(String[] words) {
        Set<Character> charSet = new HashSet<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); ++i) {
                charSet.add(word.charAt(i));
            }
        }
        return charSet;
    }
}
