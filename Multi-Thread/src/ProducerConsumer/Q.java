package ProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class Q {
    private Queue<Integer> queue;
    private final int limit;

    public Q(int limit) {
        this.queue = new LinkedList<>();
        this.limit = limit;
    }

    public synchronized void put(Integer i) {
        while (queue.size() == limit) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (queue.size() == 0) {
            notifyAll();
        }
        queue.offer(i);
    }

    public synchronized Integer take() {
        while (queue.size() == 0) {
            try {
                wait();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (queue.size() == limit) {
            notifyAll();
        }
        return queue.poll();
    }
}
