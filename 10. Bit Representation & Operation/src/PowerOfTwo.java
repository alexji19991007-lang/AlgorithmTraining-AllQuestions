import java.util.*;

public class PowerOfTwo {
    public boolean isPowerOfTwo(int number) {
        return (number & (number - 1)) == 0 && number > 0;
    }
}
