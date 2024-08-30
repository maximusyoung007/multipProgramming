package threadsecurity;

import java.util.concurrent.TimeUnit;

public class VisibilityDemo {
}

class TimeConsumingTask implements Runnable {
    private boolean toCancel;
    @Override
    public void run() {

    }

    private boolean doExecute() throws InterruptedException {
        boolean isDone = false;
        System.out.println("executing...");

        //模拟程序运行了50s
        TimeUnit.MILLISECONDS.sleep(50);

        return isDone;
    }

    public void cancel() {
        toCancel = true;
        System.out.println(this + "canceled");
    }
}
