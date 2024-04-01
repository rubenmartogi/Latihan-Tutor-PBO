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
        
        PaymentMethod[] paymentMethods = PaymentMethod.values();
        if (paymentMethodIndex >= 0 && paymentMethodIndex < paymentMethods.length) {
            PaymentMethod paymentMethod = paymentMethods[paymentMethodIndex];

            Payment payment;
            if (paymentMethod == PaymentMethod.CASH) {
                payment = new CashPayment(totalAmount);
                System.out.print("Masukkan jumlah uang yang dibayarkan: ");
                double paidAmount = scanner.nextDouble();
                if (paidAmount >= totalAmount) {
                    double change = payment.calculateChange(paidAmount);
                    System.out.println("Kembalian: " + change);
                } else {
                    double remainingAmount = totalAmount - paidAmount;
                    System.out.println("Uang yang dibayarkan kurang. Silakan bayar tambahan sebesar: " + remainingAmount);
                }
            } else if (paymentMethod == PaymentMethod.CARD) {
                System.out.print("Masukkan saldo pada kartu Anda: ");
                double cardBalance = scanner.nextDouble();
                if (cardBalance >= totalAmount) {
                    payment = new CardPayment(totalAmount, cardBalance);
                    double change = payment.calculateChange(cardBalance);
                    if (change >= 0) {
                        System.out.println("Pembayaran berhasil dengan kartu.");
                        System.out.println("Sisa saldo pada kartu Anda: " + ((CardPayment) payment).getBalance());
                    } else {
                        System.out.println("Maaf, saldo pada kartu Anda tidak mencukupi.");
                    }
                } else {
                    System.out.println("Maaf, saldo pada kartu Anda tidak mencukupi.");
                }
            }
        } else {
            System.out.println("Metode pembayaran tidak valid.");
        }

        scanner.close();
    }
}
