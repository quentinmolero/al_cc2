package fr.moleroq.al.application;

import fr.moleroq.al.domain.UserId;
import fr.moleroq.al.kernel.Command;

import java.util.Date;

public class CreatePayment implements Command {

    public final Date date;
    public final long amount;
    public final UserId userId;

    public CreatePayment(Date date, long amount, UserId userId) {
        this.date = date;
        this.amount = amount;
        this.userId = userId;
    }
}
