package fr.moleroq.al.domain;

import java.util.Objects;

public final class UserId {

    private final int value;

    private UserId(int value) {
        this.value = value;
    }

    public static UserId of(int value) {
        return new UserId(value);
    }

    public String getValue() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return value == userId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "UserId{" +
                "value=" + value +
                '}';
    }
}
