public class ArrayHopper1 {
    public boolean canJump(int[] array) {
        // int lastPos = array.length - 1;
        // // [1, 3, 2, 0, 3]
        // // 假设我们现在要跳到lastPos，只要前面任意一格可以跳到lastPos，我们就把lastPos设置成那一格。
        // // 这样下来，我们只要保证更前面的格子可以跳到新的lastPos就行了，一直以此类推往前推。
        // // 最终我们要保证我们最后的lastPos是index 0。
        // for (int i = array.length - 2; i >= 0; --i) {
        //   if (i + array[i] >= lastPos) {
        //     lastPos = i;
        //   }
        // }
        // return lastPos == 0;

        // DP方法: 从后往前走
        int n = array.length;
        boolean[] jump = new boolean[n];
        jump[n - 1] = true;
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i; j <= Math.min(array[i] + i, n - 1); ++j) {
                if (jump[j]) {
                    jump[i] = true;
                    break;
                }
            }
        }
        return jump[0];
    }
}
