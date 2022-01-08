package fr.moleroq.al.application;

import fr.moleroq.al.domain.Payment;
import fr.moleroq.al.domain.PaymentId;
import fr.moleroq.al.domain.PaymentRepository;
import fr.moleroq.al.kernel.EventListener;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public final class UserSubscriptionListener implements EventListener<UserStartSubscription> {

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

    @Override
    public String toString() {
        return "UserSubscriptionListener{" +
                "paymentRepository=" + paymentRepository +
                ", paymentService=" + paymentService +
                ", userPaymentService=" + userPaymentService +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSubscriptionListener that = (UserSubscriptionListener) o;
        return Objects.equals(paymentRepository, that.paymentRepository) && Objects.equals(paymentService, that.paymentService) && Objects.equals(userPaymentService, that.userPaymentService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentRepository, paymentService, userPaymentService);
    }
}
