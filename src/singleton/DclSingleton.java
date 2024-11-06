package singleton;

/**
 * @author maximusyoung
 * 双重检查单例模式
 */
public class DclSingleton {
    private static volatile DclSingleton instance = null;

    private DclSingleton(){}

    public static DclSingleton getInstance() {
        if (instance == null) {
            synchronized (DclSingleton.class) {
                if (instance == null) {
                    //重排列会导致instance返回不为null，但是也没有初始化完毕的实例
                    //使用volatile禁止重排序
                    instance = new DclSingleton();
                }
                return instance;
            }
        }
        return instance;
    }
}
