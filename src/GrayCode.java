import java.util.ArrayList;
import java.util.List;

public class GrayCode {
    public static void main(String[] args) {
        List<Integer> res = grayCode(3);
        double diff = Integer.MAX_VALUE;
        System.out.println(res.toString());
    }

//    Say the example input is 3.
//            0 000
//            1 001
//            3 011
//            2 010
//
//            6 110
//            7 111
//            5 101
//            4 100
//    For the pair of (2, 6), (3, 7), (1, 5) and (0, 4), the last 2 bits are the same.
//    The only difference is 6,7,5 and 4 set the first bit on. Hope this helps.


    public static List<Integer> grayCode(int n) {
        List<Integer> rs = new ArrayList<>();
        rs.add(0);
        for (int i = 0; i < n; i++) {
            int size = rs.size();
            for (int k = size - 1; k >= 0; k--)
                rs.add(rs.get(k) | 1 << i);
        }
        return rs;
    }
}
