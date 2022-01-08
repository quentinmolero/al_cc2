package fr.moleroq.al.exposition;

import fr.moleroq.al.domain.PaymentId;

import java.util.Date;
import java.util.Objects;

public final class PaymentDTO {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDTO that = (PaymentDTO) o;
        return amount == that.amount && Objects.equals(paymentId, that.paymentId) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, amount, date);
    }
}
