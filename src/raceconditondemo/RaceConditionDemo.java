package raceconditondemo;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * maximus         2024/8/23      create
 */
public class RaceConditionDemo {
    public static void main(String[] args) {
        int numberOfThreads = 4;

        Thread[] wordThreads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            wordThreads[i] = new WorkerThread(i, 10);
        }

        for (Thread t : wordThreads) {
            t.start();
        }
    }
    static class WorkerThread extends Thread {
        private final int requestCount;

        WorkerThread(int id, int requestCount) {
            //通过调用 super("worker" + id) 设置了线程的名字
            super("worker" + id);
            this.requestCount = requestCount;
        }

        @Override
        public void run() {
            int i = requestCount;
            String requestID;

            RequestIDGenerator requestIDGenerator = RequestIDGenerator.getInstance();
            while (i-- > 0) {
                requestID = requestIDGenerator.nextID();

                try {
                    processResult(requestID);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void processResult(String requestID) throws InterruptedException {
            Thread.sleep(50);
            System.out.println(Thread.currentThread().getName() + "got requestID:" + requestID);
        }
    }
}
