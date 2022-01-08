package fr.moleroq.al.application;

import fr.moleroq.al.domain.Payment;
import fr.moleroq.al.domain.PaymentId;
import fr.moleroq.al.domain.PaymentRepository;
import fr.moleroq.al.kernel.CommandHandler;
import fr.moleroq.al.kernel.Event;
import fr.moleroq.al.kernel.EventDispatcher;

import java.util.Objects;

public final class CreatePaymentCommandHandler implements CommandHandler<CreatePayment, PaymentId> {

    private final PaymentRepository paymentRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public CreatePaymentCommandHandler(PaymentRepository paymentRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.paymentRepository = paymentRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    @Override
    public PaymentId handle(CreatePayment createPayment) {
        final PaymentId paymentId = paymentRepository.nextIdentity();
        Payment payment = Payment.of(createPayment.amount, createPayment.date, paymentId);
        paymentRepository.save(payment);
        eventEventDispatcher.dispatch(new UserNewPayment(createPayment.userId, paymentId));
        return paymentId;
    }

    @Override
    public String toString() {
        return "CreatePaymentCommandHandler{" +
                "paymentRepository=" + paymentRepository +
                ", eventEventDispatcher=" + eventEventDispatcher +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatePaymentCommandHandler that = (CreatePaymentCommandHandler) o;
        return Objects.equals(paymentRepository, that.paymentRepository) && Objects.equals(eventEventDispatcher, that.eventEventDispatcher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentRepository, eventEventDispatcher);
    }
}
