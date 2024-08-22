package SimpleThreadDemo;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * maximus         2024/8/21      create
 */
public class ThreadDemoInRunMain {
    public static void main(String[] args) {
        //Thread类的run方法是public，可以直接调用，但是直接调用run()方法，不是在指定线程种允许，而是全部在main线程中运行
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.run();

        RunnableDemo runnableDemo = new RunnableDemo();
        new Thread(runnableDemo).run();

        System.out.println("3.this is main Thread, thread name: " + Thread.currentThread().getName());

        /**
         * 输出：
         * 1.this is thread demo, thread name:main
         * 2.this is runnable demo, thread name:main
         * 3.this is main Thread, thread name: main
         */
    }
}
