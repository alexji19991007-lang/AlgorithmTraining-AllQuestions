public class JuiceClient {
    private static final JuiceFactory mJuiceFactory = new JuiceFactory();

    public static void main(String[] args) {
        Juice juice1 = mJuiceFactory.getJuice("Apple");
        juice1.makeJuice();
    }
}
