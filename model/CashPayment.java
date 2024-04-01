package model;

// Kelas turunan untuk pembayaran tunai
public class CashPayment extends Payment {
    public CashPayment(double amount) {
        super(amount);
    }

    // Override metode calculateChange untuk pembayaran tunai
    @Override
    public double calculateChange(double paidAmount) {
        return paidAmount >= getAmount() ? paidAmount - getAmount() : -1; // Jika uang yang dibayarkan kurang, kembalikan -1
    }
}
