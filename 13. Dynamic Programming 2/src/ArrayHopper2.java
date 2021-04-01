public class ArrayHopper2 {
    public int minJump(int[] array) {
        int[] jump = new int[array.length];
        jump[0] = 0;
        for (int i = 1; i < jump.length; ++i) {
            jump[i] = -1;
            for (int j = i - 1; j >= 0; --j) {
                // 3 Conditions:
                //      1. We can jump to our current index i from j
                //      2. The index j that we will jump from is reachable
                //      3. Either this is the first time we find a path, or we have a shorter path
                if (array[j] + j >= i && jump[j] != -1 && (jump[i] == -1 || jump[i] > 1 + jump[j]))   {
                    jump[i] = jump[j] + 1;
                }
            }
        }
        return jump[jump.length - 1];
    }

    public int minJump2(int[] array) {
        int n = array.length;
        int[] jump = new int[n];
        jump[n - 1] = 0;
        for (int i = n - 2; i >= 0; --i) {
            jump[i] = -1;
            for (int j = i + 1; j <= Math.min(array[i] + i, n - 1); ++j) {
                if (array[i] + i >= j && jump[j] != -1 && (jump[i] == -1 || jump[i] > 1 + jump[j])) {
                    jump[i] = jump[j] + 1;
                }
            }
        }
        return jump[0];
    }
}
