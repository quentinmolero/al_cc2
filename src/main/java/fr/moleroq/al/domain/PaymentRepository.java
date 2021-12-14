package fr.moleroq.al.domain;

import java.util.List;

public interface PaymentRepository {
    void save(Payment payment);

    Payment byId(PaymentId paymentId);

    PaymentId nextIdentity();

    List<Payment> findAll();
}
