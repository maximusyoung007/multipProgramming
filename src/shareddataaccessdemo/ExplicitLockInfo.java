package shareddataaccessdemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * maximus         2024/9/3      线程转储
 * 线程转储中包含一个线程等待哪些锁以及这些锁的当前持有线程
 * jstack pid
 */
public class ExplicitLockInfo {
    private static final Lock lock = new ReentrantLock();
    private static int sharedData = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    try {
                        Thread.sleep(2200000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sharedData = 1;
                } finally {
                    lock.unlock();
                }
            }
        });

        t.start();
        Thread.sleep(1000);
        lock.lock();
        try {
            System.out.println("sharedData:" + sharedData);
        } finally {
            lock.lock();
        }
    }


}
