package ProducerConsumer;

public class Consumer implements Runnable {
    Q q;

    public Consumer(Q q) {
        super();
        this.q = q;
    }

    @Override
    public void run() {
        System.out.println(q.take());
    }
}
