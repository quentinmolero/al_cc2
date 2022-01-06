package fr.moleroq.al.application;

import fr.moleroq.al.domain.UserId;
import fr.moleroq.al.kernel.Query;

public class RetrieveUser implements Query {

    public final UserId userId;

    public RetrieveUser(UserId userId) {
        this.userId = userId;
    }
}
