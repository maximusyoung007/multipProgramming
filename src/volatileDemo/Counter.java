package volatileDemo;

/**
 * 使用锁和volatile实现简易读写锁
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * maximus         2024/10/10      create
 */
public class Counter {
    private volatile long count;

    public long value() {
        return count;
    }

    public void increment() {
        synchronized (this) {
            count++;
        }
    }
}
