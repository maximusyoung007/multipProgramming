package shareddataaccessdemo;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * maximus         2024/9/3      create
 */
public class FinallyTest {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        int a = 1;
        try {
            a = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("执行finally中的内容");
        }

        System.out.println("finally之后的内容");
    }
}
