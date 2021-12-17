package fr.moleroq.al.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentCredentials {

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
}
