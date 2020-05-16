import java.util.LinkedList;
import java.util.Scanner;

public class btd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num = sc.nextLine();
        int numTests = Integer.parseInt(num);
        for (int i = 0; i <  numTests; ++i) {
            String[] inputLine = sc.nextLine().split(" ");
            int[] nums = new int[inputLine.length];
            for (int j = 0; j < inputLine.length; ++j) {
                nums[j] = Integer.parseInt(inputLine[j]);
            }
            String res = shuffle(nums);
            System.out.println(res);
        }
    }

    public static String shuffle(int[] deck) {
        LinkedList<Integer> table = new LinkedList<>();
        for (int card : deck) {
            table.add(card);
        }
        LinkedList<Integer> hand = new LinkedList<>();
        for (Integer c : table) {
            if (hand.size() > 1) {
                hand.addFirst(hand.pollLast());
            }
            hand.addFirst(c);
        }
        StringBuilder res = new StringBuilder();
        int n = hand.size();
        for (int i = 0; i < n; ++i) {
            if (i == 0) {
                res.append(hand.pollFirst());
            } else {
                res.append(" ").append(hand.pollFirst());
            }
        }
        return res.toString();
    }
}
