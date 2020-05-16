// Client
public class SingletonClient {
    public static void main(String[] args) {
        // WE CANNOT DO THIS.
        // Singleton mySingle = new Singleton();
        Singleton mySingle = Singleton.getInstance();
        System.out.println(mySingle.getMsg());
        mySingle.setMsg("New Message");
        System.out.println(mySingle.getMsg());

        Singleton1 mySingle1 = Singleton1.getInstance();
        System.out.println(mySingle1.getMsg());
        mySingle1.setMsg("New Message");
        System.out.println(mySingle1.getMsg());
    }
}
