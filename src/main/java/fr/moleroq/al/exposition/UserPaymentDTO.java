package fr.moleroq.al.exposition;

import fr.moleroq.al.domain.PaymentId;
import fr.moleroq.al.domain.UserId;

import java.util.List;
import java.util.Objects;

public final class UserPaymentDTO {

    public final UserId userId;
    public final List<PaymentId> paymentIdList;

    public UserPaymentDTO(UserId userId, List<PaymentId> paymentIdList) {
        this.userId = userId;
        this.paymentIdList = paymentIdList;
    }

    @Override
    public String toString() {
        return "UserPaymentDTO{" +
                "userId=" + userId +
                ", paymentIdList=" + paymentIdList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPaymentDTO that = (UserPaymentDTO) o;
        return Objects.equals(userId, that.userId) && Objects.equals(paymentIdList, that.paymentIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, paymentIdList);
    }
}
