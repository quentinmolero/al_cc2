package fr.moleroq.al.domain;

import java.util.Date;

public class Payment {

    private final PaymentCredentials paymentCredentials;

    private Payment(long amount, Date date, PaymentId paymentId) {
        this.paymentCredentials = PaymentCredentials.of(amount, date, paymentId);
    }

    public static Payment of(long amount, Date date, PaymentId paymentId) {
        return new Payment(amount, date, paymentId);
    }

    public long getAmount() {
        return this.paymentCredentials.getAmount();
    }

    public PaymentId getPaymentId() {
        return this.paymentCredentials.getPaymentId();
    }
}
