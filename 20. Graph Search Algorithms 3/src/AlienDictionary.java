import java.util.*;

public class AlienDictionary {
    public static void main(String[] args) {
        String[] words = {"wwww", "wrt", "wrf", "er", "ett", "fftt"};
        AlienDictionary test = new AlienDictionary();
        System.out.println(test.alienOrder(words));
    }

    public String alienOrder(String[] words) {
        //corner case
        if (words == null || words.length == 0) {
            return null;
        }
        // inDegree to record how many incoming edges (i.e. how many letters have higher priority over this letter)
        int[] inDegree = new int[26];
        // key: character c1 of higher priority
        // value: characters that we know having lower priority than c1
        Map<Character, Set<Character>> graph = new HashMap<>();
        if (!createGraph(words, inDegree, graph)) {
            return "";
        }
        return topoSort(inDegree, graph);//run a bfs to expand all the nodes(chars)

    }

    private boolean createGraph(String[] words, int[] inDegree, Map<Character, Set<Character>> graph) {
        //iterate all the words to find all the unique chars(nodes) and put in the graph
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
            }
        }
        //compare every 2 words in sequence to find the in/out relation (edges)
        for (int i = 1; i < words.length; i++) {
            String first = words[i - 1];
            String second = words[i];
            int minLength = Math.min(first.length(), second.length());
            for (int j = 0; j < minLength; j++) {
                char higherChar = first.charAt(j); // c1
                char lowerChar = second.charAt(j); // c2
                if (higherChar != lowerChar) {
                    // c1 has higher priority than c2
                    // We make an edge from c1 --> c2
                    if (graph.get(higherChar).add(lowerChar)) {
                        // the lower-priority character c2 has one more incoming edge,
                        // thus we increment its in-degree by 1.
                        inDegree[lowerChar - 'a']++;
                    }
                    break;
                }
                // Consider cases like: ["abc", "ab"]
                // The inner for loop has reached the end, meaning that the first k characters of two strings are the
                // same, but the longer string has higher priority. Clearly this case is invalid.
                if (j == minLength - 1 && first.length() > second.length()) {
                    return false;
                }
            }
        }
        return true;
    }

    private String topoSort(int[] inDegree, Map<Character, Set<Character>> graph) {
        Queue<Character> q = new ArrayDeque<>();
        StringBuilder s = new StringBuilder();
        // Initialize the queue with all the nodes with 0 in-degree (i.e. has highest priority).
        for (char c : graph.keySet()) {
            if (inDegree[c - 'a'] == 0) {
                q.offer(c);
            }
        }
        while (!q.isEmpty()) {
            char out = q.poll();
            s.append(out);
            for (char in : graph.get(out)) {
                // decrement the in-degree of corresponding lower-priority characters.
                inDegree[in - 'a']--;
                // If the lower priority character has no more incoming edges, then add it to the queue.
                if (inDegree[in - 'a'] == 0) {
                    q.offer(in);
                }
            }
        }
        // We must expand all nodes in the graph. Otherwise, there are some unreachable characters, invalid case.
        return s.length() == graph.size() ? s.toString() : "";
    }
}
