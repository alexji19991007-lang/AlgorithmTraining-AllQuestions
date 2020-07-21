public class Power {
    public long power(int a, int b) {
        if (a == 0) {
            return 0;
        }
        if (a == 1) {
            return 1;
        }
        if (b == 0) {
            return 1;
        }
        if (b == 1) {
            return a;
        }
        long half = power(a, b / 2);
        return half * half * (b % 2 == 0 ? 1 : a);
    }
}
