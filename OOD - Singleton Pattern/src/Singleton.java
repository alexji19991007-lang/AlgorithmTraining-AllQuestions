// Eager Initialization
// 当开销比较大的时候用eagerSing
public class Singleton {
    private static final Singleton INSTANCE = new Singleton("This is a Singleton (eager)");

    private String msg;

    private Singleton(String msg) {
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
