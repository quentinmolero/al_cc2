package fr.moleroq.al.application;

import fr.moleroq.al.domain.UserId;
import fr.moleroq.al.kernel.Query;

public class RetrieveUserPayment implements Query {
    public final UserId userId;

    public RetrieveUserPayment(UserId userId) {
        this.userId = userId;
    }
}
