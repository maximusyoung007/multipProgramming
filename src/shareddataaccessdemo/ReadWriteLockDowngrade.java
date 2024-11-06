package shareddataaccessdemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//读写锁降级，写锁可以继续获得响应的读锁
public class ReadWriteLockDowngrade {
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    public void operationWithLockDowngrade() {
        boolean readLockAcquired = false;

        writeLock.lock();

        try {
            //写数据
            //申请读锁
            readLock.lock();
            readLockAcquired = true;
        } finally {
            writeLock.unlock();
        }

        if (readLockAcquired) {
            try {
                //读取共享数据
            } finally {
                readLock.unlock();
            }
        }
    }
}
