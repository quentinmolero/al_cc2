package fr.moleroq.al.domain;

import java.util.List;
import java.util.Map;

public interface UserPaymentRepository {
    void save(UserId userId, PaymentId paymentId);

    List<PaymentId> byUserId(UserId userId);

    Map<UserId, List<PaymentId>> findAll();
}
