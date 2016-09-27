package category.system;

public class BlockingQueueDemo {

    public static void main(String[] args) {
        BlockingQueue queue = new BlockingQueue(3);

        Thread thread = new Thread(new EnqueueRun("a", queue), "a");
        thread.start();

        Thread thread2 = new Thread(new DequeueRun("b", queue), "b");
        thread2.start();

    }

}

class DequeueRun implements Runnable {

    private BlockingQueue queue;

    private String threadName;

    DequeueRun(String name, BlockingQueue queue) {
        threadName = name;
        this.queue = queue;

        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try {
            for (int i = 0; i < 20; i++) {
                System.out.println("Deque: " + threadName + ", " + i);
                int j = (int) queue.dequeue();
                System.out.println("Deque: " + threadName + ", " + i + "dequed: " + j);
                Thread.sleep(1000 * i);
            }
        } catch (InterruptedException e) {
            System.out.println("Deque " + threadName + " interrupted.");
        }
        System.out.println("Deque " + threadName + " exiting.");
    }

}

class EnqueueRun implements Runnable {

    private BlockingQueue queue;

    private String threadName;

    EnqueueRun(String name, BlockingQueue queue) {
        threadName = name;
        this.queue = queue;

        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try {
            for (int i = 0; i < 20; i++) {
                System.out.println("Enque: " + threadName + ", " + i);
                queue.enqueue(new Integer(i));
                System.out.println("Enque: " + threadName + ", " + i + "enqued " + i);
                Thread.sleep(1000 - 20 * i);
            }
        } catch (InterruptedException e) {
            System.out.println("Enque " + threadName + " interrupted.");
        }
        System.out.println("Enque " + threadName + " exiting.");
    }

}
