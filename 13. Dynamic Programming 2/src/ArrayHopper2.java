public class ArrayHopper2 {
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
