package fr.moleroq.al.infrastructure;

import fr.moleroq.al.domain.PaymentId;
import fr.moleroq.al.domain.UserId;
import fr.moleroq.al.domain.UserPaymentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryUserPaymentRepository implements UserPaymentRepository {

    private final Map<UserId, List<PaymentId>> data = new ConcurrentHashMap<>();

    @Override
    public void save(UserId userId, PaymentId paymentId) {
        var userPayments = data.get(userId);
        if (userPayments == null) {
            List<PaymentId> paymentIdList = new ArrayList<>();
            paymentIdList.add(paymentId);
            data.put(userId, paymentIdList);
        } else {
            userPayments.add(paymentId);
        }
    }

    @Override
    public List<PaymentId> byUserId(UserId userId) {
        final List<PaymentId> paymentIdList = data.get(userId);
        if (paymentIdList == null || paymentIdList.size() == 0) {
            throw new RuntimeException("No payment for user " + userId.getValue());
        }
        return List.copyOf(paymentIdList);
    }

    @Override
    public Map<UserId, List<PaymentId>> findAll() {
        return new ConcurrentHashMap<>(data);
    }
}
