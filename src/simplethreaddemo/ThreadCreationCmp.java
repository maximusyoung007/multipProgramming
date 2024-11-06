package simplethreaddemo;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * maximus         2024/8/21      create
 */
public class ThreadCreationCmp {
    public static void main(String[] args) {

        final int processorsNum = Runtime.getRuntime().availableProcessors();

        Thread t;
        CountingTask ct = new CountingTask();

        //实现Runnable接口的方式， count < 2 * 核心数 * 100
        for (int i = 0; i < 2 * processorsNum; i++) {
            t = new Thread(ct);
            t.start();
        }

        //继承Thread类的方式， count < 100
        for (int i = 0; i < 2 * processorsNum; i++) {
            t = new CountingThread();
            t.start();
        }
    }

    static class Counter {
        private int count = 0;

        public void increment() {
            count++;
        }

        public int value() {
            return count;
        }
    }

    static class CountingTask implements Runnable {
        private Counter counter = new Counter();

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                counter.increment();
                System.out.println("CountingTask" + counter.value());
            }
        }
    }

    static class CountingThread extends Thread {
        private Counter counter = new Counter();

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                counter.increment();
                System.out.println("CountingThread" + counter.value());
            }
        }
    }

}
