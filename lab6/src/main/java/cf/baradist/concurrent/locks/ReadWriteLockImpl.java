package cf.baradist.concurrent.locks;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Simple fair ReadWriteLock implementation
 */
public class ReadWriteLockImpl implements ReadWriteLock {
    private final Lock writeLock;
    private final Lock readLock;
    private final AtomicBoolean isWriting = new AtomicBoolean(false);
    private final AtomicInteger readersCount = new AtomicInteger();
    private final AtomicInteger waitingWritersCount = new AtomicInteger();
    private final ConcurrentLinkedQueue<Thread> queue = new ConcurrentLinkedQueue<>();

    public ReadWriteLockImpl() {
        readLock = new Lock() {
            @Override
            public void lock() {
                queue.add(Thread.currentThread());
                synchronized (ReadWriteLockImpl.this) {
                    while (queue.peek() != Thread.currentThread()) {
                        monitorWait();
                    }
                    queue.poll();
                    ReadWriteLockImpl.this.notifyAll();
                    readersCount.incrementAndGet();
                }
            }

            @Override
            public void unlock() {
                synchronized (ReadWriteLockImpl.this) {
                    readersCount.decrementAndGet();
                    ReadWriteLockImpl.this.notifyAll();
                }
            }
        };
        writeLock = new Lock() {
            @Override
            public void lock() {
                queue.add(Thread.currentThread());
                synchronized (ReadWriteLockImpl.this) {
                    waitingWritersCount.incrementAndGet();
                    while (queue.peek() != Thread.currentThread() || readersCount.get() > 0) {
                        monitorWait();
                    }
                    waitingWritersCount.decrementAndGet();
                    isWriting.set(true);
                }
            }

            @Override
            public void unlock() {
                synchronized (ReadWriteLockImpl.this) {
                    queue.poll();
                    isWriting.set(false);
                    ReadWriteLockImpl.this.notifyAll();
                }
            }
        };
    }

    @Override
    public Lock readLock() {
        return readLock;
    }

    @Override
    public Lock writeLock() {
        return writeLock;
    }

    public boolean getIsWriting() {
        return isWriting.get();
    }

    public int getReadersCount() {
        return readersCount.get();
    }

    public int getWaitingWritersCount() {
        return waitingWritersCount.get();
    }

    private void monitorWait() {
        try {
            ReadWriteLockImpl.this.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
