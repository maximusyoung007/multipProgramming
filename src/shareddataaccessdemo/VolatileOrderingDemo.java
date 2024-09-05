package shareddataaccessdemo;

/**
 * volatile的有序性
 * volatile的写操作之前的读、写操作会先于volatile写操作被提交
 * 其他线程看到volatile变量更新时，写线程在更新volatile变量前所执行的内存操作对于读线程是可见的
 */
public class VolatileOrderingDemo {
    private int dataA = 0;
    private long dataB = 0L;
    private String dataC = null;
    private volatile boolean ready = false;

    public void writer() {
        dataA = 1;
        dataB = 10000L;
        dataC = "Content...";
        ready = true;
    }

    public int reader() {
        int result = 0;
        boolean allISOK;
        if (ready) {
            allISOK = (1 == dataA) && (10000L == dataB) && "Content...".equals(dataC);
            result = allISOK ? 1 : 2;
        } else {
            result = 3;
        }
        return result;
    }

    public static void main(String[] args) {
        VolatileOrderingDemo demo = new VolatileOrderingDemo();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(demo.reader());
                    demo.writer();
                    System.out.println(demo.reader());
                }
            });
            thread.start();
        }
    }
}
