package designPattern;

/**
 * @author maximusyoung
 */
public class Singleton {
    public volatile static Singleton singleton;

    public Singleton init() {
        if (singleton == null) {
            synchronized (this) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
