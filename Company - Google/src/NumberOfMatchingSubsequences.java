import java.util.ArrayList;
import java.util.List;

// LeetCode 792
public class NumberOfMatchingSubsequences {
    static class Node {
        String word;
        int index;
        public Node(String word, int i) {
            this.word = word;
            this.index = i;
        }
    }

    // TC: O(S.length() + Sum of all words' length), in the worst case you need to iterate over all the letters of the words.
    // SC: O(words.length)
    public int numMatchingSubseq(String s, String[] words) {
        int res = 0;
        ArrayList<Node>[] buckets = new ArrayList[26];
        for (int i = 0; i < buckets.length; ++i) {
            buckets[i] = new ArrayList<>();
        }
        for (String word : words) {
            buckets[word.charAt(0) - 'a'].add(new Node(word, 0));
        }
        for (int i = 0; i < s.length(); ++i) {
            char cur = s.charAt(i);
            ArrayList<Node> oldBucket = buckets[cur - 'a'];
            buckets[cur - 'a'] = new ArrayList<>();
            for (Node n : oldBucket) {
                n.index++;
                if (n.index == n.word.length()) {
                    res++;
                } else {
                    buckets[n.word.charAt(n.index) - 'a'].add(n);
                }
            }
        }
        return res;
    }
}
