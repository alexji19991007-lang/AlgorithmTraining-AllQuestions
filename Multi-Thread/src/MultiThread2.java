public class MultiThread2 {
    public static void main(String[] args) {
        Thread newThread = new Thread((java.lang.Runnable)new HelloRunnable());
        newThread.start();
    }
}
