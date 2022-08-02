package Codility;

public class SendMessage {
    public static void main(String[] args) {
        SendMessage test = new SendMessage();
        System.out.println(test.sendMessage("cdeenetpi", new int[] {5, 2, 0, 1, 6, 4, 8, 3, 7}));
    }

    public String sendMessage(String S, int[] A) {
        StringBuilder sb = new StringBuilder();
        int curIndex = 0;
        boolean stop = false;
        for (int i = 0; i < S.length(); ++i) {
            if (stop) {
                return sb.toString();
            }
            sb.append(S.charAt(curIndex));
            curIndex = A[curIndex];
            if (curIndex == 0) {
                stop = true;
            }
        }
        return sb.toString();
    }
}
