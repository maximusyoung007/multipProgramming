package simplethreaddemo;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * 杨文俊         2024/8/21      create
 */
public class TestRunMain {
    public static void main(String[] args) {
        TestRunGrandSon testRunGrandSon = new TestRunGrandSon();
        testRunGrandSon.run();

        TestRunDaughter testRunDaughter = new TestRunDaughter();
        new TestRunSon(testRunDaughter);

        // 一个调用的是TestRunSon的实现类，一个直接调用的TestRunSon的实现类，所以用的run方法不一样
        // Thread和Thread实现类 同理
    }
}
