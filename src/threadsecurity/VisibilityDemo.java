package threadsecurity;

import java.util.concurrent.TimeUnit;

public class VisibilityDemo {
    public static void main(String[] args) throws InterruptedException {
        TimeConsumingTask timeConsumingTask = new TimeConsumingTask();
        Thread thread = new Thread(timeConsumingTask);
        thread.start();
        //Thread.sleep(1000);
        TimeUnit.SECONDS.sleep(1);
        timeConsumingTask.cancel();
    }
}

class TimeConsumingTask implements Runnable {
    // volatile保证toCancel的可见性，但是实测发现，即使不加，主线程中更新toCancel，子线程也能看到
    private volatile boolean toCancel = false;
    @Override
    public void run() {
        try {
            while (!toCancel) {

                if (doExecute()) {
                    break;
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (toCancel) {
            System.out.println("Task was canceled");
        } else {
            System.out.println("Task Done");
        }
    }

    private boolean doExecute() throws InterruptedException {
        boolean isDone = false;
        System.out.println("executing...");

        //模拟程序运行了5s
        TimeUnit.MILLISECONDS.sleep(50);

        return isDone;
    }

    public void cancel() {
        toCancel = true;
        System.out.println(this + "canceled");
        System.out.println(toCancel);
    }
}
