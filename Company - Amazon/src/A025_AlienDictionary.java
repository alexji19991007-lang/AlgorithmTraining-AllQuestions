import java.util.*;

public class A025_AlienDictionary {
    // TC: O(L) --> L the total length of all the words in the input list, added together.
    // OC: O(U + min(U^2, N)) --> U is the # of unique letters
    //                        --> N is the total number of strings in the array
    //            The adjacency list uses most memory, which is O(V + E). V --> U
    //            E --> min(U^2, N) --> maximum number of relations (U^2 is the maximum number of edges)
    // In this problem U is 26 in the worst case, so it is actually O(1)
    public String alienOrder(String[] words) {
        // This graph is an adjacency list, where the key is a character and the values
        // are a set of characters that have lower priorities than the key
        // E.g.: 't' : <'x', 'y', 'z'>
        //       1. This means x, y and z all comes after t in the alien alphabet
        //       2. This also means in a virtual graph, t has outgoing edges pointing toward
        //          x, y, and z (imagine each character as a node).
        Map<Character, Set<Character>> graph = new HashMap<>();
        // Get all characters occurred in the array
        Set<Character> charSet = getCharSet(words);
        // Build the adjacency list
        if (!buildGraph(words, graph)) {
            return "";
        }
        return topologicalSort(graph, charSet);
    }

    public String topologicalSort(Map<Character, Set<Character>> graph, Set<Character> charSet) {
        int numChars = charSet.size();
        char[] topologicalOrder = new char[numChars];
        Map<Character, Integer> incomingEdges = new HashMap<>();
        for (char x : charSet) {
            incomingEdges.put(x, 0);
        }
        // Set up a map of Character --> incoming edges
        // If a character has 5 incoming edges, then there are at least 5 characters coming before it
        // in the alien alphabet
        for (char x : charSet) {
            // next: outgoing edges of node x
            Set<Character> next = graph.getOrDefault(x, null);
            if (next == null) {
                continue;
            }
            for (char y : next) {
                incomingEdges.put(y, incomingEdges.get(y) + 1);
            }
        }
        // All the nodes entering the queue will have no incoming edges.
        Queue<Character> q = new ArrayDeque<>();
        for (char x : incomingEdges.keySet()) {
            // The character with no incoming edges could be the first character
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
                // If an edge has 0 incoming edges after a node has been removed,
                // put it to the queue as well
                if (newIncomingEdgeCount == 0) {
                    q.offer(y);
                }
            }
        }
        // we must make sure that all characters are expanded
        return numExpanded == numChars ? new String(topologicalOrder) : "";
    }

    public boolean buildGraph(String[] words, Map<Character, Set<Character>> graph) {
        for (int i = 0; i < words.length - 1; ++i) {
            String s1 = words[i];
            String s2 = words[i + 1];
            int minLength = Math.min(s1.length(), s2.length());
            for (int k = 0; k < minLength; ++k) {
                char c1 = s1.charAt(k);
                char c2 = s2.charAt(k);
                if (c1 != c2) {
                    Set<Character> adjList = graph.getOrDefault(c1, new HashSet<>());
                    adjList.add(c2);
                    graph.put(c1, adjList);
                    break;
                }
                // Take care of cases like "abc", "ab", where we should immediately return false
                if (k == minLength - 1) {
                    if (s1.length() > s2.length()) {
                        return false;
                    }
                }
            }
        }
        return true;
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
