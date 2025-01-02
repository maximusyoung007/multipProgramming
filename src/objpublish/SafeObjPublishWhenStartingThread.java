package objpublish;

import java.util.Map;

/**
 * @author maximusyoung
 */
public class SafeObjPublishWhenStartingThread {
    //final保证对象对外可见时，该字段一定是初始化的
    private final Map<String, String> objectState;

    public SafeObjPublishWhenStartingThread(Map<String, String> objectState) {
        this.objectState = objectState;
        //不能在构造器中启动工作线程，防止this逸出，可能会得到一个未初始化完毕的对象
    }

    private void init() {
        new Thread() {
            @Override
            public void run() {
                String value = objectState.get("hello");
            }
        }.start();
    }

    //工厂方法
    public static SafeObjPublishWhenStartingThread newInstance(Map<String, String> objectState) {
        SafeObjPublishWhenStartingThread instance = new SafeObjPublishWhenStartingThread(objectState);
        instance.init();
        return instance;
    }
}
