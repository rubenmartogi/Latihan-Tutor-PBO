package driver;

import java.util.Scanner;
import model.Payment;
import model.CashPayment;
import model.CardPayment;
import model.PaymentMethod;

public class PaymentApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan total pembayaran: ");
        double totalAmount = scanner.nextDouble();

        System.out.print("Pilih metode pembayaran (0: Tunai, 1: Kartu): ");
        int paymentMethodIndex = scanner.nextInt();
        PaymentMethod paymentMethod = PaymentMethod.values()[paymentMethodIndex];

        Payment payment;
        if (paymentMethod == PaymentMethod.CASH) {
            payment = new CashPayment(totalAmount);
            System.out.print("Masukkan jumlah uang yang dibayarkan: ");
            double paidAmount = scanner.nextDouble();
            double change = payment.calculateChange(paidAmount);
            if (change >= 0) {
                System.out.println("Kembalian: " + change);
            } else {
                System.out.println("Maaf, uang yang dibayarkan kurang.");
            }
        } else if (paymentMethod == PaymentMethod.CARD) {
            payment = new CardPayment(totalAmount);
            System.out.println("Pembayaran berhasil dengan kartu.");
        } else {
            System.out.println("Metode pembayaran tidak valid.");
        }

        scanner.close();
    }
}
