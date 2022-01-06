package fr.moleroq.al.exposition;

import fr.moleroq.al.domain.PaymentId;

import java.util.Date;

public class PaymentDTO {

    public final PaymentId paymentId;
    public final long amount;
    public final Date date;

    public PaymentDTO(PaymentId paymentId, long amount, Date date) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "paymentId='" + paymentId + '\'' +
                ", amount='" + amount + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
