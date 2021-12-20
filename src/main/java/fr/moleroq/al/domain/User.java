package fr.moleroq.al.domain;

public final class User {

    private final UserCredentials userCredentials;
    private final UserStateHistory history;

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
        user.changeState(UserState.CREATED_WITH_ERROR);
        throw new IllegalArgumentException("Illegal arguments");
    }

    public void changeState(UserState userState) {
        this.history.append(userState);
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

    @Override
    public String toString() {
        return "User{" +
                "userCredentials=" + userCredentials +
                '}';
    }
}
