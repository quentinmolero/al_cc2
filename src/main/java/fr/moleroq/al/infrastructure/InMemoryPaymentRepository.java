package fr.moleroq.al.infrastructure;

import fr.moleroq.al.domain.Payment;
import fr.moleroq.al.domain.PaymentId;
import fr.moleroq.al.domain.PaymentRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryPaymentRepository implements PaymentRepository {

    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<PaymentId, Payment> data = new ConcurrentHashMap<>();

    @Override
    public void save(Payment payment) {
        data.put(payment.getPaymentId(), payment);
    }

    @Override
    public Payment byId(PaymentId paymentId) {
        final Payment payment = data.get(paymentId);
        if (payment == null) {
            throw new RuntimeException("No payment for " + paymentId.getValue());
        }
        return payment;
    }

    @Override
    public PaymentId nextIdentity() {
        return PaymentId.of(counter.incrementAndGet());
    }

    @Override
    public List<Payment> findAll() {
        return List.copyOf(data.values());
    }
}
