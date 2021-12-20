package fr.moleroq.al.domain;

import java.util.ArrayList;
import java.util.List;

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
}
