package singleton;

/**
 * @author maximusyoung
 * 加锁单例模式
 */
public class SimpleMultiSingleton {
    private static SimpleMultiSingleton instance = null;

    private SimpleMultiSingleton() { }

    public static SimpleMultiSingleton getInstance() {
        synchronized (SimpleMultiSingleton.class) {
            if (instance == null) {
                instance = new SimpleMultiSingleton();
            }
            return instance;
        }
    }
}
