// When will the JVM exit? -- no alive non-daemon threads
// Two ways of creating threads and make them run

// 1) Extends Thread class
class HelloThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello World1");
    }
}

// 2) Implements runnable
interface Runnable {
    void run();
}

// Implements只是说明他可以并行，但是并不是并行的独立的Thread
class HelloRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100000; ++i) {
            // 如果没有interrupt的话，被中断也不理
            // Interrupted 是一个static method，谁调用就找谁的
            if (Thread.interrupted()) {
                return;
            }
        }
    }
}
