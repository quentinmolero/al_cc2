package fr.moleroq.al.domain;

import java.util.Objects;

public final class PaymentId {

    private final int value;

    private PaymentId(int value) {
        this.value = value;
    }

    public static PaymentId of(int value) {
        return new PaymentId(value);
    }

    public String getValue() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentId paymentId = (PaymentId) o;
        return value == paymentId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "PaymentId{" +
                "value=" + value +
                '}';
    }
}
