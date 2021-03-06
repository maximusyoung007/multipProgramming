import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountWithoutSync {
    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            executor.execute(new AddAPennyTask());
        }

        executor.shutdown();

        while (!executor.isTerminated()) {}

        System.out.println("what is balance?" + account.getBalance());
    }

    private static class AddAPennyTask implements Runnable {
        @Override
        public void run() {
            //方法二：给对象加锁
            synchronized (account) {
                account.deposit(1);
            }
        }
    }

    private static class Account {
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        //方法一：synchronized一次只有一个线程可以访问这个方法
//        public synchronized void deposit(int amount) {
//            int newBalance = balance + amount;
//
//            try {
//                Thread.sleep(5);
//            } catch (InterruptedException e) {
//
//            }
//
//            balance = newBalance;
//        }

        public void deposit(int amount) {
            int newBalance = balance + amount;

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {

            }

            balance = newBalance;
        }
    }
}

