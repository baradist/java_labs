package cf.baradist.concurrent.locks;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Simple ReadWriteLock implementation
 * NOTE: writers oppress readers!
 */
public class ReadWriteLockImpl implements ReadWriteLock {
    private final Lock writeLock;
    private final Lock readLock;
    private final AtomicBoolean isWriting = new AtomicBoolean(false);
    private final AtomicInteger readersCount = new AtomicInteger();
    private final AtomicInteger waitingWritersCount = new AtomicInteger();

    public ReadWriteLockImpl() {
        readLock = new Lock() {
            @Override
            public void lock() {
                synchronized (ReadWriteLockImpl.this) {
                    while (isWriting.get() || waitingWritersCount.get() > 0) {
                        monitorWait();
                    }
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
                synchronized (ReadWriteLockImpl.this) {
                    waitingWritersCount.incrementAndGet();
                    while (isWriting.get() || readersCount.get() > 0) {
                        monitorWait();
                    }
                    waitingWritersCount.decrementAndGet();
                    isWriting.set(true);
                }
            }

            @Override
            public void unlock() {
                synchronized (ReadWriteLockImpl.this) {
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
