package NewThreadDemo;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * maximus         2024/8/21      create
 */
public class ThreadDemoMain {
    public static void main(String[] args) {
        //Thread有两种主要构造器 Thread() 及 Thread(Runnable)
        //创建线程有两种方式，继承 Thread类，或者实现Runnable接口
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();

        RunnableDemo runnableDemo = new RunnableDemo();
        new Thread(runnableDemo).start();

        System.out.println("3.this is main Thread, thread name: " + Thread.currentThread().getName());
    }
}
