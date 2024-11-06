package threadsecurity;

import java.util.concurrent.TimeUnit;

public class ThreadStartVisibility {
    //线程间共享变量
    // 父线程在启动子线程之前对共享变量的更新对子线程是可见的
    static int data = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                    System.out.println(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        data = 1;
        thread.start();

        TimeUnit.MILLISECONDS.sleep(50);
        data = 2;
    }
}
