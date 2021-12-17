package fr.moleroq.al.domain;

public final class User {

    private final UserCredentials userCredentials;

    private User(UserId userId, String lastname, String firstname, String password) {
        this.userCredentials = UserCredentials.of(userId, lastname, firstname, password);
    }

    public static User of(UserId userId, String lastname, String firstname, String password) {
        User user = new User(userId, lastname, firstname, password);
        if (ValidationUserEngine.getInstance().test(user)) {
            return user;
        }
        throw new IllegalArgumentException("Illegal arguments");
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
