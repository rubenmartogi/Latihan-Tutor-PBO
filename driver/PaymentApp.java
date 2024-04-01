package driver;

import java.util.Scanner;
import model.Payment;
import model.CashPayment;
import model.CardPayment;
import model.PaymentMethod;

public class PaymentApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter total payment amount: ");
        double totalAmount = scanner.nextDouble();

        System.out.print("Choose payment method (0: Cash, 1: Card): ");
        int paymentMethodIndex = scanner.nextInt();

        PaymentMethod[] paymentMethods = PaymentMethod.values();
        if (paymentMethodIndex >= 0 && paymentMethodIndex < paymentMethods.length) {
            PaymentMethod paymentMethod = paymentMethods[paymentMethodIndex];

            Payment payment;
            if (paymentMethod == PaymentMethod.CASH) {
                payment = new CashPayment(totalAmount);
                System.out.print("Enter the amount of money paid: ");
                double paidAmount = scanner.nextDouble();
                if (paidAmount >= totalAmount) {
                    double change = payment.calculateChange(paidAmount);
                    System.out.println("Change: " + change);
                } else {
                    double remainingAmount = totalAmount - paidAmount;
                    System.out.println("The amount paid is insufficient. Please pay an additional: " + remainingAmount);
                }
            } else if (paymentMethod == PaymentMethod.CARD) {
                System.out.print("Enter the balance on your card: ");
                double cardBalance = scanner.nextDouble();
                if (cardBalance >= totalAmount) {
                    payment = new CardPayment(totalAmount, cardBalance);
                    double change = payment.calculateChange(cardBalance);
                    if (change >= 0) {
                        System.out.println("Payment successful with card.");
                        System.out.println("Remaining balance on your card: " + ((CardPayment) payment).getBalance());
                    } else {
                        System.out.println("Sorry, insufficient balance on your card.");
                    }
                } else {
                    System.out.println("Sorry, insufficient balance on your card.");
                }
            }
        } else {
            System.out.println("Invalid payment method.");
        }

        scanner.close();
    }
}
