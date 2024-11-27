package singleton;

/**
 * @author maximusyoung
 * 基于静态内部类实现的单例模式
 */
public class StaticHolderSingleton {
    private StaticHolderSingleton() { }

    private static class InstanceHolder {
        final static StaticHolderSingleton INSTANCE = new StaticHolderSingleton();
    }

    public static StaticHolderSingleton getInstance() {
        //静态变量被访问时，会初始化该类，静态变量的值是初始值为非默认值
        //静态变量只会初始化一次
        return InstanceHolder.INSTANCE;
    }
}
