package fr.moleroq.al.domain;

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
}
