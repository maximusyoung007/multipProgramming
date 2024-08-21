package NewThreadDemo;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * maximus         2024/8/21      create
 */
public class ThreadDemo extends Thread {
    @Override
    public void run() {
        System.out.println("1.this is thread demo, thread name:" + Thread.currentThread().getName());
    }
}
