package fr.moleroq.al.domain;

import java.util.Date;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "Payment{" +
                "paymentCredentials=" + paymentCredentials +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(paymentCredentials, payment.paymentCredentials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentCredentials);
    }
}
