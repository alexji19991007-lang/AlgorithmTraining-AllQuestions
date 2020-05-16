import java.util.*;

public class AlienDictionary {
    private static final int WHITE = 1;
    private static final int GRAY = 2;
    private static final int BLACK = 3;

    // We will use adjacency lists to represent the relationships of characters
    // In a list, if x -> a, b, c, then it means x is before a, b and c in the alphabet
    private Map<Character, List<Character>> graph;
    // Use a hashset to store all appearing characters
    private Set<Character> charSet;
    // Use a color map to show the visit status of all nodes
    private Map<Character, Integer> color;
    // Use a boolean value to show if there exists a cycle, in which case a valid ordering is impossible
    private boolean isPossible;

    public String alienOrder(String[] words) {
        this.graph = new HashMap<>();
        this.charSet = new HashSet<>();
        this.color = new HashMap<>();
        isPossible = true;
        buildGraph(words);
        return topoSort();
    }

    // Establish the adjacency lists
    public void buildGraph(String[] words) {
        // Find all chars in the words (duplicates are only counted once)
        getCharSet(words);
        for (int i = 0; i < words.length - 1; ++i) {
            for (int j = i + 1; j < words.length; ++j) {
                String s1 = words[i];
                String s2 = words[j];
                for (int k = 0; k < Math.min(s1.length(), s2.length()); ++k) {
                    char c1 = s1.charAt(k);
                    char c2 = s2.charAt(k);
                    // if c1 != c2, then establish the ordering of c1 and c2. Since c1 is in s1
                    // which comes before s2 that contains c2, c1 should be placed before c2 in the
                    // alphabet. Thus, c2 is in c1's adjacency list
                    if (c1 != c2) {
                        // Remove the chars that are in the graph from charSet.
                        // If the char is not in the set, no removal is made
                        charSet.remove(c1);
                        charSet.remove(c2);
                        List<Character> adjList = graph.getOrDefault(c1, new ArrayList<>());
                        if (!adjList.contains(c2)) {
                            adjList.add(c2);
                        }
                        graph.put(c1, adjList);
                        // Once we found the first different chars of two strings, stop going
                        break;
                    }
                }
            }
        }
    }

    // Do the topological sort by DFS to find the correct sequence of alphabet
    public String topoSort() {
        StringBuilder ans = new StringBuilder();
        for (Character v : graph.keySet()) {
            if (color.get(v) == WHITE) {
                // Pick a vertex, if it's not visited, start DFS on it
                visitDFS(ans, v);
            }
        }
        // If not possible, just return empty string
        if (!isPossible) {
            return "";
        }
        // Find characters that have not been processed
        for (Character c : charSet) { // insert orphan chars in the front.
            ans.insert(0, c);
        }
        // Reverse the string builder, and we get the solution
        return ans.reverse().toString();
    }

    public void getCharSet(String[] words) {
        for (String word : words) {
            for (char c : word.toCharArray()) {
                charSet.add(c);
                // Initialize all nodes to be unvisited
                color.put(c, WHITE);
            }
        }
    }

    private void visitDFS(StringBuilder ans, char node) {
        // If we already found a cycle, it is impossible to build an ordering, immediately return
        if (!isPossible) {
            return;
        }
        // This node is discovered, but not finished
        color.put(node, GRAY);
        // Get the adjacency list of the current node
        for (Character neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            // If this neighbor is not discovered yet, continue DFS on this neighbor
            if (color.get(neighbor) == WHITE) {
                visitDFS(ans, neighbor);
            }
            // If the neighbor is discovered, but not finished, then we have found a cycle, so no way to find a valid ordering
            else if (color.get(neighbor) == GRAY) {
                isPossible = false;
            }
        }
        // This node is finished, paint it to black
        color.put(node, BLACK);
        // append the node to res
        ans.append(node);
    }
}
