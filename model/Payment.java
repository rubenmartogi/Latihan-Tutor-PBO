package model;

// Kelas utama untuk pembayaran
public class Payment {
    private double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    // Metode untuk menghitung kembalian
    public double calculateChange(double paidAmount) {
        return paidAmount - amount;
    }
}
