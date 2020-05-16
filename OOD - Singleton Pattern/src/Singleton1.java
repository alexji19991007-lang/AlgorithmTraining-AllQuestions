// Lazy Initialization
public class Singleton1 {
    private static volatile Singleton1 INSTANCE = null;

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
