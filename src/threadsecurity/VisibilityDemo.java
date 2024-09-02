package threadsecurity;

import java.util.concurrent.TimeUnit;

public class VisibilityDemo {
    public static void main(String[] args) throws InterruptedException {
        TimeConsumingTask timeConsumingTask = new TimeConsumingTask();
        Thread thread = new Thread(new TimeConsumingTask());
        thread.start();
        //Thread.sleep(1000);
        TimeUnit.SECONDS.sleep(1);
        timeConsumingTask.cancel();
    }
}

class TimeConsumingTask implements Runnable {
    private volatile boolean toCancel = false;
    @Override
    public void run() {
        try {
            while (!toCancel) {

                if (doExecute()) {
                    break;
                }
                TimeUnit.MILLISECONDS.sleep(10);
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
        TimeUnit.MILLISECONDS.sleep(500);

        return isDone;
    }

    public void cancel() {
        toCancel = true;
        System.out.println(this + "canceled");
        System.out.println(toCancel);
    }
}
