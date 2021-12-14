package fr.moleroq.al.domain;

public class PaymentCredentials {

    private final long amount;
    private final PaymentId paymentId;

    private PaymentCredentials(long amount, PaymentId paymentId) {
        this.amount = amount;
        this.paymentId = paymentId;
    }

    public static PaymentCredentials of (long amount, PaymentId paymentId) {
        return new PaymentCredentials(amount, paymentId);
    }

    public long getAmount() {
        return amount;
    }

    public PaymentId getPaymentId() {
        return paymentId;
    }
}
