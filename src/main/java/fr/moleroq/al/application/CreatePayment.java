package fr.moleroq.al.application;

import fr.moleroq.al.domain.UserId;
import fr.moleroq.al.kernel.Command;

import java.util.Date;
import java.util.Objects;

public final class CreatePayment implements Command {

    public final Date date;
    public final long amount;
    public final UserId userId;

    public CreatePayment(Date date, long amount, UserId userId) {
        this.date = date;
        this.amount = amount;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CreatePayment{" +
                "date=" + date +
                ", amount=" + amount +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatePayment that = (CreatePayment) o;
        return amount == that.amount && Objects.equals(date, that.date) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, userId);
    }
}
