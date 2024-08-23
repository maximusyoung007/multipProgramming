package simplethreaddemo;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * maximus         2024/8/21      create
 */
public class RunnableDemo implements Runnable{
    @Override
    public void run() {
        System.out.println("2.this is runnable demo, thread name:" + Thread.currentThread().getName());
    }
}
