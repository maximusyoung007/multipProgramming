package singleton;

/**
 * @author maximusyoung
 * 双重检查单例模式
 */
public class DclSingleton {
    private static volatile DclSingleton instance = null;

    private DclSingleton(){}

    public static DclSingleton getInstance() {
        //重排列会导致第一次检查时instance返回不为null，但是也没有初始化完毕的实例
        //使用volatile禁止重排序
        if (instance == null) {
            synchronized (DclSingleton.class) {
                if (instance == null) {
                    instance = new DclSingleton();
                }
                return instance;
            }
        }
        return instance;
    }
}
