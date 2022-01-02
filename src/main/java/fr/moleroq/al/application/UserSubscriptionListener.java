package fr.moleroq.al.application;

import fr.moleroq.al.domain.Payment;
import fr.moleroq.al.domain.PaymentId;
import fr.moleroq.al.domain.PaymentRepository;
import fr.moleroq.al.kernel.EventListener;

import java.time.Instant;
import java.util.Date;

public class UserSubscriptionListener implements EventListener<UserStartSubscription> {

    private final PaymentRepository paymentRepository;
    private final PaymentService paymentService;
    private final UserPaymentService userPaymentService;

    public UserSubscriptionListener(PaymentRepository paymentRepository, PaymentService paymentService, UserPaymentService userPaymentService) {
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
        this.userPaymentService = userPaymentService;
    }

    @Override
    public void listenTo(UserStartSubscription event) {
        final PaymentId paymentId = paymentRepository.nextIdentity();
        Payment payment = Payment.of(10_00, Date.from(Instant.now()), paymentId);
        paymentService.create(payment);
        userPaymentService.create(event.getUser(), paymentRepository.byId(paymentId));
    }
}
