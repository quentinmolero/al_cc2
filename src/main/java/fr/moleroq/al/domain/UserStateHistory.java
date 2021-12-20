package fr.moleroq.al.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class UserStateHistory {

    private final List<UserState> history;

    private UserStateHistory(List<UserState> history) {
        this.history = history;
    }

    public static UserStateHistory create(UserState userState) {
        return new UserStateHistory(List.of(userState));
    }

    public UserStateHistory append(UserState userState) {
        List<UserState> newStates = new ArrayList<>(history);
        newStates.add(userState);
        return new UserStateHistory(newStates);
    }

    public List<UserState> getStates() {
        return List.copyOf(history);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStateHistory that = (UserStateHistory) o;
        return Objects.equals(history, that.history);
    }

    @Override
    public int hashCode() {
        return Objects.hash(history);
    }

    @Override
    public String toString() {
        return "UserStateHistory{" +
                "history=" + history +
                '}';
    }
}
