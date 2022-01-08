package fr.moleroq.al.application;

import fr.moleroq.al.domain.PaymentRepository;
import fr.moleroq.al.exposition.PaymentDTO;
import fr.moleroq.al.kernel.QueryHandler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class RetrievePaymentsHandler implements QueryHandler<RetrievePayments, List<PaymentDTO>> {

    private final PaymentRepository paymentRepository;

    public RetrievePaymentsHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<PaymentDTO> handle(RetrievePayments query) {
        return paymentRepository.findAll()
                .stream()
                .map(payment ->
                        new PaymentDTO(payment.getPaymentId(), payment.getAmount(),
                                payment.getPaymentDate()))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "RetrievePaymentsHandler{" +
                "paymentRepository=" + paymentRepository +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetrievePaymentsHandler that = (RetrievePaymentsHandler) o;
        return Objects.equals(paymentRepository, that.paymentRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentRepository);
    }
}
