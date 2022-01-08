package fr.moleroq.al.application;

import fr.moleroq.al.domain.Payment;
import fr.moleroq.al.domain.PaymentRepository;
import fr.moleroq.al.exposition.PaymentDTO;
import fr.moleroq.al.kernel.QueryHandler;

import java.util.Objects;

public final class RetrievePaymentHandler implements QueryHandler<RetrievePayment, PaymentDTO> {

    private final PaymentRepository paymentRepository;

    public RetrievePaymentHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentDTO handle(RetrievePayment query) {
        Payment payment = paymentRepository.byId(query.paymentId);
        return new PaymentDTO(payment.getPaymentId(), payment.getAmount(), payment.getPaymentDate());
    }

    @Override
    public String toString() {
        return "RetrievePaymentHandler{" +
                "paymentRepository=" + paymentRepository +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetrievePaymentHandler that = (RetrievePaymentHandler) o;
        return Objects.equals(paymentRepository, that.paymentRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentRepository);
    }
}
