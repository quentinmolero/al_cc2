package fr.moleroq.al.exposition;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

final class PaymentRequest {

    @NotNull
    @NotBlank
    @DecimalMin("1")
    public long amount;

    @NotNull
    @NotBlank
    public String userId;

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "amount=" + amount +
                ", userId='" + userId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentRequest that = (PaymentRequest) o;
        return amount == that.amount && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, userId);
    }
}
