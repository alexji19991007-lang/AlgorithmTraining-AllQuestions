import java.util.ArrayList;
import java.util.List;

public class CanMakePalindromeFromString {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> res = new ArrayList<>();
        int[][] preSumCount = new int[s.length() + 1][26];
        for (int i = 0; i < s.length(); ++i) {
            preSumCount[i + 1] = preSumCount[i].clone();
            preSumCount[i + 1][s.charAt(i) - 'a']++;
        }
        for (int[] query :queries){
            int sum = 0;
            for (int i = 0 ; i < 26; ++i) {
                int curCharCount = preSumCount[query[1] + 1][i] - preSumCount[query[0]][i];
                sum = curCharCount % 2 == 0 ? sum : sum + 1;
            }
            int minChanges = sum / 2;
            res.add(minChanges <= query[2]);
        }
        return res;
    }
}
