package shareddataaccessdemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockUsage {
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    public void reader() {
        readLock.lock();
        try {

        } finally {

            readLock.unlock();
        }
    }

    public void writer() {
        writeLock.lock();
        try {

        } finally {
            writeLock.unlock();
        }
    }
}
