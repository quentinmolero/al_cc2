package fr.moleroq.al.application;

import fr.moleroq.al.domain.PaymentId;
import fr.moleroq.al.kernel.Query;

import java.util.Objects;

public final class RetrievePayment implements Query {
    public final PaymentId paymentId;

    public RetrievePayment(PaymentId paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return "RetrievePayment{" +
                "paymentId=" + paymentId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetrievePayment that = (RetrievePayment) o;
        return Objects.equals(paymentId, that.paymentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId);
    }
}
