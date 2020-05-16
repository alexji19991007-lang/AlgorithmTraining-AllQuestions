public class Power {
    public double power(double base, int exp) {
        if (exp == 0) return 1; //任何数的0次方都是 1
        if (exp == 1) return base;//任何数的1 都是自己
        if (base == 1) return 1;
        if (base == -1 && exp % 2 == 0) {
            return 1;
        } else if (base == -1 && exp % 2 != 0) {
            return -1;
        }
        if (base == 0) {
            if (exp < 0)
                return Double.POSITIVE_INFINITY;//0的负数次方都是 无穷大
            else
                return 0;//0的正数次方都是0
        }
        double half = power(base, Math.abs(exp) >> 1);
        //这里判断如何当前是奇数次数 还需要再乘以一个自己
        double ret = ((exp & 1) == 1 ? base : 1.0) * half * half;
        if (exp < 0)//如果这个整数是负数的时候  则取倒数
            ret = 1 / ret;
        return ret;
    }
}
