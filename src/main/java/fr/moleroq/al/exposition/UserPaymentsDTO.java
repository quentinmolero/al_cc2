package fr.moleroq.al.exposition;

import java.util.List;
import java.util.Objects;

final class UserPaymentsDTO {
    public List<UserPaymentDTO> userPayments;

    @Override
    public String toString() {
        return "UserPaymentsDTO{" +
                "userPayments=" + userPayments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPaymentsDTO that = (UserPaymentsDTO) o;
        return Objects.equals(userPayments, that.userPayments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userPayments);
    }
}
