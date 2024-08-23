package simplethreaddemo;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * 杨文俊         2024/8/21      create
 */
public class TestRunSon implements TestRun {
    private TestRun testRun;

    public TestRunSon() {}

    public TestRunSon(TestRun testRun) {
        this.testRun = testRun;
    }

    @Override
    public void run() {
        if (testRun != null) {
            testRun.run();
        }
    }
}
