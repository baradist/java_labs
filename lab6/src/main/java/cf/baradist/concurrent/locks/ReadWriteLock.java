package cf.baradist.concurrent.locks;

public interface ReadWriteLock {
    Lock readLock();

    Lock writeLock();

    interface Lock {
        void lock();

        void unlock();
    }
}
