// Lazy Initialization
public class Singleton1 {
    // 可见性（visibility）
    // 多线程情况下：
    //    线程 A 创建了 INSTANCE
    //    线程 B 可能看不到最新值
    //👉 volatile 的作用：
    //    一个线程修改后，其他线程立刻能看到
    private volatile static Singleton1 INSTANCE = null;

    private String msg;

    private Singleton1(String msg) {
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public static Singleton1 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton1.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton1("This is a Singleton (lazy)");
                }
            }
        }
        return INSTANCE;
    }
}
