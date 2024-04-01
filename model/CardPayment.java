package model;

public class CardPayment extends Payment {
    private double balance;

    public CardPayment(double totalAmount, double balance) {
        super(totalAmount);
        this.balance = balance;
    }

    @Override
    public double calculateChange(double paidAmount) {
        if (paidAmount >= getAmount()) {
            deductBalance(getAmount());
            return paidAmount - getAmount();
        }
        return -1; // Jika uang yang dibayarkan kurang, kembalikan -1
    }

    private void deductBalance(double amount) {
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
