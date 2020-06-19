public class ArrayHopper3 {
    public static void main(String[] args) {
        ArrayHopper3 test = new ArrayHopper3();
        int[] array = {4, 2, 1, 3, 2, 1, 0, 4};
        System.out.println(test.minJump(array));
    }

    public int minJump(int[] array) {
        int[] M = new int[array.length];
        for (int i = array.length - 1; i >= 0; --i) {
            M[i] = Integer.MAX_VALUE;
            for (int j = i + 1; j <= i + array[i] && j < array.length + 1; ++j) {
                if (j >= array.length) {
                    M[i] = 1;
                    break;
                } else if (M[j] != Integer.MAX_VALUE && M[i] > M[j] + 1) {
                    M[i] = M[j] + 1;
                }
            }
        }
        return M[0] == Integer.MAX_VALUE ? -1 : M[0];
    }
}
