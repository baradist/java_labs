package cf.baradist;

import cf.baradist.concurrent.locks.ReadWriteLockImpl;

class Database {
    private final ReadWriteLockImpl readWriteLock = new ReadWriteLockImpl();

    private static void sleep() {
        try {
            Thread.sleep((long) (2000 * Math.random()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void read() {
        try {
            startRead();
            sleep();
        } finally {
            endRead();
        }
    }

    void write() {
        try {
            startWrite();
            sleep();
        } finally {
            endWrite();
        }
    }

    private void startRead() {
        log("wants to read  ");
        readWriteLock.readLock().lock();
        log("reading        ");
    }

    private void endRead() {
        log("finish reading ");
        readWriteLock.readLock().unlock();
    }

    private void startWrite() {
        log("WANTS TO WRITE ");
        readWriteLock.writeLock().lock();
        log("WRITING        ");
    }

    private void endWrite() {
        log("FINISH WRITING ");
        readWriteLock.writeLock().unlock();
    }

    private void log(String s) {
        System.out.println(Thread.currentThread().getName() + " " + s
                + ".\t is writing: " + readWriteLock.getIsWriting()
                + ", writers are waiting: " + readWriteLock.getWaitingWritersCount()
                + " readers are reading: " + readWriteLock.getReadersCount()
        );
    }
}
