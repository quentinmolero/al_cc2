package fr.moleroq.al.domain;

import java.util.Date;

public final class Payment {

    private final PaymentCredentials paymentCredentials;

    private Payment(long amount, Date date, PaymentId paymentId) {
        this.paymentCredentials = PaymentCredentials.of(amount, date, paymentId);
    }

    public static Payment of(long amount, Date date, PaymentId paymentId) {
        Payment payment =  new Payment(amount, date, paymentId);
        if (ValidationPaymentEngine.getInstance().test(payment)) {
            return payment;
        }
        throw new IllegalStateException("Illegal arguments");
    }

    public long getAmount() {
        return this.paymentCredentials.getAmount();
    }

    public Date getPaymentDate() {
        return this.paymentCredentials.getDate();
    }

    public PaymentId getPaymentId() {
        return this.paymentCredentials.getPaymentId();
    }
}
