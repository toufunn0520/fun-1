package category.system;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueue {

    private int limit = 10;

    private List queue = new LinkedList();

    public BlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized Object dequeue() throws InterruptedException {
        while (this.queue.size() == 0) {
            System.out.println("in dequeue method: current queue size is " + queue.size());
            wait();
        }
        if (this.queue.size() == this.limit) {
            System.out.println("in dequeue2 method: current queue size is " + queue.size());
            notifyAll();
        }

        return this.queue.remove(0);
    }

    public synchronized void enqueue(Object item) throws InterruptedException {
        while (this.queue.size() == this.limit) {
            System.out.println("in enqueue method: current queue size is " + queue.size());
            wait();
        }
        if (this.queue.size() == 0) {
            System.out.println("in enqueue method2: current queue size is " + queue.size());
            notifyAll();
        }
        this.queue.add(item);
    }

}
