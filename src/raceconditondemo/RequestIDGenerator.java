package raceconditondemo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * maximus         2024/8/23      create
 */
public class RequestIDGenerator {
    private final static RequestIDGenerator INSTANCE = new RequestIDGenerator();
    private final static short SQE_UPPER_LIMIT = 999;
    private short sequence = -1;

    private RequestIDGenerator() {

    }

    //synchronized修饰方法，保证一次只有一个线程执行该方法
//    public synchronized short nextSequence() {
//        //多线程场景下，有可能第一个线程拿到了sequence=1，第二个线程进来了，但是第一个线程还没来得及更新sequence，所以第二个线程拿到的也是1，造成重复，即读脏数据
//        //也有可能是第二个线程拿到旧的sequence，然后第一个线程已经更新了sequence，那么第二个线程是用就的值去做if判断
//        // 解决方法之一是在方法上加synchronized
//        if (sequence > SQE_UPPER_LIMIT) {
//            sequence = 0;
//        } else {
//            sequence++;
//        }
//        return sequence;
//    }

    //同步实例方法相当于以"this"为引导锁的同步块（锁句柄）
//    public short nextSequence() {
//        synchronized (this) {
//            if (sequence > SQE_UPPER_LIMIT) {
//                sequence = 0;
//            } else {
//                sequence++;
//            }
//            return sequence;
//        }
//    }

    //显式锁实现同步
//    private final Lock lock = new ReentrantLock();
//    public short nextSequence() {
//        lock.lock();
//
//        try {
//            if (sequence > SQE_UPPER_LIMIT) {
//                sequence = 0;
//            } else {
//                sequence++;
//            }
//            return sequence;
//        } finally {
//            lock.unlock();
//        }
//    }

    //使用tryLock()进行同步
    //ReentrantLock表示是可重入锁，表示线程自身可以多次请求同一把锁，不会自己导致自己死锁
    private final Lock lock = new ReentrantLock();
    public short nextSequence() {
        while (true) {
            if (lock.tryLock()) {
                try {
                    if (sequence > SQE_UPPER_LIMIT) {
                        sequence = 0;
                    } else {
                        sequence++;
                    }
                    return sequence;
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public String nextID() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String timeStamp = sdf.format(new Date());
        DecimalFormat df = new DecimalFormat("000");

        short sequence = nextSequence();

        return "0049" + timeStamp + df.format(sequence);
    }

    //单例模式， 通过此方法可以保证每次获取到的都是同一个实例,才可以保证每次获得的序列号都是递增的
    public static RequestIDGenerator getInstance() {
        return INSTANCE;
    }
}
