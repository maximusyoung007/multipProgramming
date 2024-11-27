package singleton;

/**
 * @author maximusyoung
 * 枚举实现单例模式
 */
public enum Singleton {
    INSTANCE;

    public Singleton getInstance() {
        return INSTANCE;
    }
}
