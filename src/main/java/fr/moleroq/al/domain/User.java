package fr.moleroq.al.domain;

public final class User {

    private final UserCredentials userCredentials;

    private User(UserId userId, String lastname, String firstname, String password) {
        this.userCredentials = UserCredentials.of(userId, lastname, firstname, password);
    }

    public static User of(UserId userId, String lastname, String firstname, String password) {
        return new User(userId, lastname, firstname, password);
    }

    public UserId getUserId() {
        return this.userCredentials.getUserId();
    }

    public void changePassword(String newPassword) {
        this.userCredentials.changePassword(newPassword);
    }

    @Override
    public String toString() {
        return "User{" +
                "userCredentials=" + userCredentials +
                '}';
    }
}
