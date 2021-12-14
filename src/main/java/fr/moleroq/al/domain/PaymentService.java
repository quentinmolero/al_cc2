package fr.moleroq.al.domain;

import java.util.List;

public final class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void create(Payment payment) {
        this.paymentRepository.save(payment);
    }

    public List<Payment> all() {
        return List.copyOf(this.paymentRepository.findAll());
    }
}
