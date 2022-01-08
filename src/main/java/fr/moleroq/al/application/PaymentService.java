package fr.moleroq.al.application;

import fr.moleroq.al.domain.Payment;
import fr.moleroq.al.domain.PaymentRepository;

import java.util.List;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "PaymentService{" +
                "paymentRepository=" + paymentRepository +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentService that = (PaymentService) o;
        return Objects.equals(paymentRepository, that.paymentRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentRepository);
    }
}
