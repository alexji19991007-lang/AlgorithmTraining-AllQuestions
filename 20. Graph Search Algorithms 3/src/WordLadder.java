import java.util.*;

public class WordLadder {
    static class NeighborFinder {
        private Map<String, Integer> wordIndex = new HashMap<>();
        private List<String> words;

        public NeighborFinder(List<String> words) {
            for (int i = 0; i < words.size(); ++i) {
                String word = words.get(i);
                wordIndex.put(word, i);
            }
            this.words = words;
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

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int endIndex = wordList.indexOf(endWord);
        if (endIndex == -1) {
            return 0;
        }
        List<String> words;
        int beginIndex = wordList.indexOf(beginWord);
        if (beginIndex == -1) {
            words = new ArrayList<>(wordList);
            words.add(beginWord);
            beginIndex = words.size() - 1;
        } else {
            words = wordList;
        }
        NeighborFinder finder = new NeighborFinder(words);
        Queue<Integer> queue = new ArrayDeque<>();
        int[] step = new int[words.size()];
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
}
