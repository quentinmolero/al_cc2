package fr.moleroq.al.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

final class PaymentCredentials {

    private final long amount;
    private final Date date;
    private final PaymentId paymentId;

    private PaymentCredentials(long amount, Date date, PaymentId paymentId) {
        this.amount = amount;
        this.date = date;
        this.paymentId = paymentId;
    }

    public static PaymentCredentials of (long amount, Date date, PaymentId paymentId) {
        return new PaymentCredentials(amount, date, paymentId);
    }

    public long getAmount() {
        return amount;
    }

    public String getPaymentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
        return simpleDateFormat.format(this.date);
    }

    public Date getDate() {
        return date;
    }

    public PaymentId getPaymentId() {
        return paymentId;
    }

    @Override
    public String toString() {
        return "PaymentCredentials{" +
                "amount=" + amount +
                ", date=" + date +
                ", paymentId=" + paymentId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentCredentials that = (PaymentCredentials) o;
        return amount == that.amount && Objects.equals(date, that.date) && Objects.equals(paymentId, that.paymentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, date, paymentId);
    }
}
