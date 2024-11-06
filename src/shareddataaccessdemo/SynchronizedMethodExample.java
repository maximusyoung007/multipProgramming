package shareddataaccessdemo;

public class SynchronizedMethodExample {
    public static synchronized void staticMethod() {

    }

    //同步静态方法相当于以当前类对象为引导锁的同步块
//    public static void staticMethod() {
//        synchronized (SynchronizedMethodExample.class) {
//
//        }
//    }
}
