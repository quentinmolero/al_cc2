package fr.moleroq.al.exposition;

import fr.moleroq.al.domain.PaymentId;
import fr.moleroq.al.domain.UserId;

import java.util.List;

public class UserPaymentDTO {

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
}
