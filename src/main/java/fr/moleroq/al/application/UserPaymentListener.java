package fr.moleroq.al.application;

import fr.moleroq.al.kernel.EventListener;

public class UserPaymentListener implements EventListener<UserNewPayment> {

    private final UserPaymentService userPaymentService;

    public UserPaymentListener(UserPaymentService userPaymentService) {
        this.userPaymentService = userPaymentService;
    }

    @Override
    public void listenTo(UserNewPayment event) {
        this.userPaymentService.create(event.getUserId(), event.getPaymentId());
    }
}
