package singleton;

/**
 * @author maximusyoung
 * 单线程单例模式
 */
public class SingleThreadSingleton {
    private static SingleThreadSingleton instance = null;

    private SingleThreadSingleton() { }

    public static SingleThreadSingleton getInstance() {
        if (instance == null) {
            instance = new SingleThreadSingleton();
        }
        return instance;
    }
}
