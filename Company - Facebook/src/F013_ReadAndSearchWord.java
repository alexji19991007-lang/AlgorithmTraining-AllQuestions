// LeetCode 211
public class F013_ReadAndSearchWord {
    // TC: O(m) for well-defined words without dots, O(m * n) for undefined words
    //     m --> length of longest inserted key, n --> the number of inserted keys
    //     Worst case: ........ (m + 1 dots, exactly one character longer than all inserted keys)
    // SC: O(1) for well-defined words without dots, O(m) for undefined words to keep the recursion stack
    static class TrieNode {
        TrieNode[] child;
        boolean isWord;

        public TrieNode() {
            this.child = new TrieNode[26];
            this.isWord = false;
        }
    }

    private final TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public F013_ReadAndSearchWord() {
        root = new TrieNode();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (node.child[ch - 'a'] == null) {
                node.child[ch - 'a'] = new TrieNode();
            }
            node = node.child[ch - 'a'];
        }
        node.isWord = true;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return searchHelper(word, root, 0);
    }

    private boolean searchHelper(String word, TrieNode node, int index) {
        if (index == word.length()) {
            return node.isWord;
        }
        if (word.charAt(index) != '.') {
            return node.child[word.charAt(index) - 'a'] != null && searchHelper(word, node.child[word.charAt(index) - 'a'], index + 1);
        }
        for (TrieNode n : node.child) {
            if (n != null && searchHelper(word, n, index + 1)) {
                return true;
            }
        }
        return false;
    }
}
