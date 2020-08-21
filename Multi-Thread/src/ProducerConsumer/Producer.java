package ProducerConsumer;

public class Producer implements Runnable {
    Q q;

    public Producer(Q q) {
        super();
        this.q = q;
    }

    @Override
    public void run() {
        q.put(0);
    }
}
