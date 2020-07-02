// Better lazy initialization
// 对于JDK 5以前的版本，不存在volatile
public class Singleton2 {
    // Static nested class只会在第一次被需要的时候加载，也就是getInstance()被invoke的时候，
    // 而SingletonHolder被加载时才会实例化Singleton2这个类(lazy initialization)。静态
    // 变量的初始化是线程安全的（静态变量初始化只执行一次），所以这种方法既保证了线程安全，又保证了
    // lazy initialization，也不依赖于JDK版本，同时没有性能缺陷，基本上是比较完美的。
    private static class SingletonHolder {
        private static final Singleton2 INSTANCE = new Singleton2("Lazy Initialization Without Volatile");
    }

    private String msg;

    private Singleton2(String msg) {
        this.msg = msg;
    }

    private void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public static final Singleton2 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
