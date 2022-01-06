package fr.moleroq.al.application;

import fr.moleroq.al.domain.PaymentId;
import fr.moleroq.al.kernel.Query;

public class RetrievePayment implements Query {
    public final PaymentId paymentId;

    public RetrievePayment(PaymentId paymentId) {
        this.paymentId = paymentId;
    }
}
