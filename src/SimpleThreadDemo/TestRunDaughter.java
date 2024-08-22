package SimpleThreadDemo;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * 杨文俊         2024/8/21      create
 */
public class TestRunDaughter implements TestRun {
    @Override
    public void run() {
        System.out.println("daughter");
    }
}
