package fr.moleroq.al.domain;

import fr.moleroq.al.kernel.Engine;

import java.util.function.Predicate;

@Engine
public class ValidationPaymentEngine implements Predicate<Payment> {

    private static final ValidationPaymentEngine INSTANCE = new ValidationPaymentEngine();

    private ValidationPaymentEngine() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Already initialized");
        }
    }

    public static ValidationPaymentEngine getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean test(Payment payment) {
        return payment != null &&
                payment.getAmount() > 0 &&
                payment.getPaymentId() != null &&
                payment.getPaymentDate() != null;
    }
}
