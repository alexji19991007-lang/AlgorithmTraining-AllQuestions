public class Factorial {
    public static void main(String[] args) {
        long result = factorial(4);
        System.out.println(result);
    }

    public static long factorial(int n) {
        if (n < 1) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }
        long result = n * factorial(n - 1);
        return result;
    }
}
