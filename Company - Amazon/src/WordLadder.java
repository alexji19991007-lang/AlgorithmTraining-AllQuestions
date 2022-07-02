import java.util.*;

// LeetCode 127
public class WordLadder {
    // TC: O(n * k^2), where n is the size of the wordList, and k is the length of each word
    // SC: O(n * k)
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // If the end word is not in the list, return 0 (transform impossible)
        int endIndex = wordList.indexOf(endWord);
        if (endIndex == -1) {
            return 0;
        }
        // If the begin word is not in the list, add it to the last of the word list
        // and set the beginIndex to the index of the begin word
        int beginIndex = wordList.indexOf(beginWord);
        if (beginIndex == -1) {
            wordList.add(beginWord);
            beginIndex = wordList.size() - 1;
        }
        NeighborFinder finder = new NeighborFinder(wordList);
        Queue<Integer> queue = new LinkedList<>();
        int[] step = new int[wordList.size()];
        Arrays.fill(step, -1);
        queue.offer(beginIndex);
        step[beginIndex] = 0;
        while (!queue.isEmpty()) {
            int x = queue.poll();
            if (x == endIndex) {
                return step[x] + 1;
            }
            for (int y : finder.findNeighbors(x)) {
                if (step[y] == -1) {
                    queue.offer(y);
                    step[y] = step[x] + 1;
                }
            }
        }
        return 0;
    }

    static class NeighborFinder {
        Map<String, Integer> wordIndex;
        List<String> words;

        public NeighborFinder(List<String> wordList) {
            this.wordIndex = new HashMap<>();
            for (int i = 0; i < wordList.size(); ++i) {
                wordIndex.put(wordList.get(i), i);
            }
            this.words = wordList;
        }

        public List<Integer> findNeighbors(int i) {
            List<Integer> neighbors = new ArrayList<>();
            String word = words.get(i);
            StringBuilder wordModifier = new StringBuilder(word);
            for (int j = 0; j < wordModifier.length(); ++j) {
                char orig = word.charAt(j);
                for (char c = 'a'; c <= 'z'; ++c) {
                    if (c == orig) {
                        continue;
                    }
                    wordModifier.setCharAt(j, c);
                    int neighbor = wordIndex.getOrDefault(wordModifier.toString(), -1);
                    if (neighbor != -1) {
                        neighbors.add(neighbor);
                    }
                }
                wordModifier.setCharAt(j, orig);
            }
            return neighbors;
        }
    }
}
