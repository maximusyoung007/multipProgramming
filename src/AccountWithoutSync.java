public class AccountWithoutSync {
    private static Account account = new Account();

    private static class Account {
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

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

