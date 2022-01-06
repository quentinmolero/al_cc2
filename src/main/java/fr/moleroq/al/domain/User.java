package fr.moleroq.al.domain;

import java.util.Objects;

public final class User {

    private final UserCredentials userCredentials;
    private UserStateHistory history;

    private User(UserId userId, String lastname, String firstname, String password, UserState initialState) {
        this.userCredentials = UserCredentials.of(userId, lastname, firstname, password);
        this.history = UserStateHistory.create(initialState);
    }

    public static User of(UserId userId, String lastname, String firstname, String password) {
        User user = new User(userId, lastname, firstname, password, UserState.IN_CREATION);
        if (ValidationUserEngine.getInstance().test(user)) {
            user.changeState(UserState.CREATED);
            return user;
        }
        user.changeState(UserState.PENDING);
        throw new IllegalArgumentException("Illegal arguments");
    }

    public void changeState(UserState userState) {
        this.history = this.history.append(userState);
    }

    public void changePassword(String newPassword) {
        this.userCredentials.changePassword(newPassword);
    }

    public UserId getUserId() {
        return this.userCredentials.getUserId();
    }

    public String getUserLastname() {
        return this.userCredentials.getLastname();
    }

    public String getUserFirstname() {
        return this.userCredentials.getFirstname();
    }

    public String getUserPassword() {
        return this.userCredentials.getPassword();
    }

    public UserStateHistory getHistory() {
        return history;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userCredentials, user.userCredentials) && Objects.equals(history, user.history);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userCredentials, history);
    }

    @Override
    public String toString() {
        return "User{" +
                "userCredentials=" + userCredentials +
                ", history=" + history +
                '}';
    }
}
