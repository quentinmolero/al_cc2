package fr.moleroq.al.application;

import fr.moleroq.al.domain.PaymentId;
import fr.moleroq.al.domain.UserId;
import fr.moleroq.al.kernel.ApplicationEvent;

import java.util.Objects;

public final class UserNewPayment implements ApplicationEvent {

    private final UserId userId;
    private final PaymentId paymentId;

    public UserNewPayment(UserId userId, PaymentId paymentId) {
        this.userId = userId;
        this.paymentId = paymentId;
    }

    public UserId getUserId() {
        return userId;
    }

    public PaymentId getPaymentId() {
        return paymentId;
    }

    @Override
    public String toString() {
        return "UserNewPayment{" +
                "userId=" + userId +
                ", paymentId=" + paymentId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNewPayment that = (UserNewPayment) o;
        return Objects.equals(userId, that.userId) && Objects.equals(paymentId, that.paymentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, paymentId);
    }
}
