
public class TaskThreadDemo {
    public static void main(String[] args) {
        Runnable r1 = new PrintChar('a', 100);
        Runnable r2 = new PrintChar('b', 100);
        Runnable r3 = new PrintNum(100);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);

        t1.start();
        t2.start();
        t3.start();
    }
}

class PrintChar implements Runnable {
    private char charToPrint;

    private int times;

    public PrintChar(char c, int i) {
        this.charToPrint = c;
        this.times = i;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            System.out.println(i + "-----" + charToPrint);
        }
    }

}

class PrintNum implements Runnable {
    private int lastNum;

    public PrintNum(int n) {
        lastNum = n;
    }

    @Override
    public void run() {
        for (int i = 0; i <= lastNum; i++) {
            System.out.println(" " + i);
        }
    }

}


