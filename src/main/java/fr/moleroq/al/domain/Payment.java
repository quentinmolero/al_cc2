package fr.moleroq.al.domain;

public class Payment {

    private final PaymentCredentials paymentCredentials;

    private Payment(long amount, PaymentId paymentId) {
        this.paymentCredentials = PaymentCredentials.of(amount, paymentId);
    }

    public static Payment of(long amount, PaymentId paymentId) {
        return new Payment(amount, paymentId);
    }

    public long getAmount() {
        return this.paymentCredentials.getAmount();
    }

    public PaymentId getPaymentId() {
        return this.paymentCredentials.getPaymentId();
    }
}
