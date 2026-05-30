public class Power {
    public int pow(int a, int b) {
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
        return a * pow(a, b - 1);
    }
    // b层，所以是O(b)
    // Because this is recursive, each call is placed on the call stack until the base case is reached.
    // Maximum recursion depth = b
    // Each stack frame uses constant space so O(b).

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
    // log(b)层，所以是O(log(b))
    // space is also O(log(b))
}
