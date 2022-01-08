package fr.moleroq.al.application;

import fr.moleroq.al.domain.UserId;
import fr.moleroq.al.kernel.Query;

import java.util.Objects;

public final class RetrieveUser implements Query {

    public final UserId userId;

    public RetrieveUser(UserId userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RetrieveUser{" +
                "userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetrieveUser that = (RetrieveUser) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
