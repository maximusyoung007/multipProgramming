package ThreadFrequentlyUsedMethod;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * maximus         2024/8/22      使用sleep()实现 简易倒计时器
 */
public class SimpleTimer {
    private static int count;

    public static void main(String[] args) {
        count = args.length >= 1 ? Integer.valueOf(args[0]) : 60;

        int remaining;

        //循环-1
        while (true) {
            remaining = countDown();
            if (0 == remaining) {
                break;
            } else {
                System.out.println("Remaining" + remaining + "seconds");
            }


            try {
                //当前线程暂停1秒
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Done");
    }

    private static int countDown() {
        return count--;
    }
}
