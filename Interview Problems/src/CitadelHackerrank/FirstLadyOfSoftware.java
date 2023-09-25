package CitadelHackerrank;

import java.math.BigInteger;

public class FirstLadyOfSoftware {
    private final BigInteger MOD = BigInteger.valueOf((long) Math.pow(10, 9) + 7);

    public static void main(String[] args) {
        FirstLadyOfSoftware test = new FirstLadyOfSoftware();
        System.out.println(test.firstLadyOfSoftware(12, 33));
        System.out.println(test.firstLadyOfSoftware_2(12, 33));
        System.out.println(test.firstLadyOfSoftware_3(12, 33));
    }

    public int firstLadyOfSoftware(int numProcesses, int numIntervals) {
        return (int) (numProcesses * Math.pow(numProcesses - 1, numIntervals - 1));
    }

    public int firstLadyOfSoftware_2(int numProcesses, int numIntervals) {
        int p = (int)Math.pow(10, 9) + 7;
        return (int) ((numProcesses % p) * power(numProcesses - 1, numIntervals - 1, p)) % p;
    }

    public int firstLadyOfSoftware_3(int numProcesses, int numIntervals) {
        BigInteger numProcessesBig = BigInteger.valueOf(numProcesses);
        BigInteger numProcessesMinusOneBig = BigInteger.valueOf(numProcesses - 1);
        BigInteger numIntervalsMinusOneBig = BigInteger.valueOf(numIntervals - 1);

        return (numProcessesBig.multiply(numProcessesMinusOneBig.modPow(numIntervalsMinusOneBig, MOD))).mod(MOD).intValue();
    }

    public long power(long a, long b, long p) {
        long x = 1, y = a;
        while (b > 0) {
            if (b % 2 == 1) {
                x = (x * y) % p;
            }
            y = (y * y) % p;
            b /= 2;
        }
        return x % p;
    }
}
