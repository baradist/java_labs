package cf.baradist.concurrent;

/**
 * Silly unfair semaphore
 */
public class Semaphore {
    private final int initialCounter;
    private volatile int counter;

    public Semaphore(int counter) {
        this.counter = counter;
        initialCounter = counter;
    }

    public int getCounter() {
        return counter;
    }

    public synchronized final void acquire() {
        acquire(1);
    }

    public synchronized final void acquire(int arg) {
        if (arg > initialCounter) {
            throw new IllegalArgumentException("Try to acquire more resources, then possible " + arg + " of " + initialCounter);
        }
        while (arg > counter) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter -= arg;
    }

    public synchronized void release() {
        release(1);
    }

    public synchronized final boolean release(int arg) {
        counter += arg;
        notify();
        return true;
    }
}
