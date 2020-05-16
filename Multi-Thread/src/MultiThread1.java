public class MultiThread1 {
    public static void main(String[] args) {
        Thread newThread = new HelloThread();
        newThread.start();
        //newThread.run(); 这个是错误的！！串行了，新的thread obj根本没有被执行
    }
}
