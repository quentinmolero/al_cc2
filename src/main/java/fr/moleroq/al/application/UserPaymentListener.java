package fr.moleroq.al.application;

import fr.moleroq.al.kernel.EventListener;

import java.util.Objects;

public final class UserPaymentListener implements EventListener<UserNewPayment> {

    private final UserPaymentService userPaymentService;

    public UserPaymentListener(UserPaymentService userPaymentService) {
        this.userPaymentService = userPaymentService;
    }

    @Override
    public void listenTo(UserNewPayment event) {
        this.userPaymentService.create(event.getUserId(), event.getPaymentId());
    }

    @Override
    public String toString() {
        return "UserPaymentListener{" +
                "userPaymentService=" + userPaymentService +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPaymentListener that = (UserPaymentListener) o;
        return Objects.equals(userPaymentService, that.userPaymentService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userPaymentService);
    }
}
