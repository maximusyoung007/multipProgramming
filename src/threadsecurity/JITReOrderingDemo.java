package threadsecurity;

//指令重排序demo, 复现不了
public class JITReOrderingDemo {
    private int externalData = 1;
    private Helper helper;

    public void createHelper() {
        helper = new Helper(externalData);
    }

    public int consume() {
        int sum = 0;
        final Helper observedHelper = helper;

        if (null == observedHelper) {
            sum = -1;
        } else {
            sum = observedHelper.payloadA + observedHelper.payloadB
                + observedHelper.payloadC + observedHelper.payloadD;
        }
        return sum;
    }

    static class Helper {
        int payloadA;
        int payloadB;
        int payloadC;
        int payloadD;

        public Helper(int externalData) {
            this.payloadA = externalData;
            this.payloadB = externalData;
            this.payloadC = externalData;
            this.payloadD = externalData;
        }
    }

    static class RunDemo implements Runnable {

        @Override
        public void run() {
            JITReOrderingDemo demo = new JITReOrderingDemo();
            demo.createHelper();
            int sum = demo.consume();
            if (sum != 4) {
                System.out.println("occurrences:" + sum);
            }
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10000000; i++) {
            RunDemo runDemo = new RunDemo();
            Thread thread = new Thread(runDemo);
            thread.start();
        }
    }
}
