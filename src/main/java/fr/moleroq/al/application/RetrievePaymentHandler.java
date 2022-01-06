package fr.moleroq.al.application;

import fr.moleroq.al.domain.Payment;
import fr.moleroq.al.domain.PaymentRepository;
import fr.moleroq.al.exposition.PaymentDTO;
import fr.moleroq.al.kernel.QueryHandler;

public class RetrievePaymentHandler implements QueryHandler<RetrievePayment, PaymentDTO> {

    private final PaymentRepository paymentRepository;

    public RetrievePaymentHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentDTO handle(RetrievePayment query) {
        Payment payment = paymentRepository.byId(query.paymentId);
        return new PaymentDTO(payment.getPaymentId(), payment.getAmount(), payment.getPaymentDate());
    }
}
