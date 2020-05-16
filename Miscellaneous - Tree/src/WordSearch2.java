import java.util.ArrayList;
import java.util.List;

public class WordSearch2 {
    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

    private char[][] board;
    private List<String> res;

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (int i = 0; i < w.length(); ++i) {
                char cur = w.charAt(i);
                int index = cur - 'a';
                if (p.next[index] == null) {
                    p.next[index] = new TrieNode();
                }
                p = p.next[index];
            }
            p.word = w;
        }
        return root;
    }

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        this.res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                dfs(i, j, root);
            }
        }
        return res;
    }

    public void dfs(int i, int j, TrieNode node) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return;
        }
        char cur = board[i][j];
        if (cur == '#' || node.next[cur -'a'] == null) {
            return;
        }
        node = node.next[cur -  'a'];
        if (node.word != null) { // found one
            res.add(node.word);
            node.word = null; // de-duplicate
        }
        // change the char to '#' so we will never use it again in this word
        // In this way we can avoid using extra space visited[][];
        board[i][j] = '#';
        dfs(i - 1, j, node);
        dfs(i, j + 1, node);
        dfs(i + 1, j, node);
        dfs(i, j - 1, node);
        board[i][j] = cur;
    }
}
