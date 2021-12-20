package fr.moleroq.al.domain;

import java.util.Objects;

public final class UserCredentials {

    private final UserId userId;
    private final String lastname;
    private final String firstname;
    private String password;

    private UserCredentials(UserId userId, String lastname, String firstname, String password) {
        this.userId = userId;
        this.lastname = lastname;
        this.firstname = firstname;
        this.password = password;
    }

    public static UserCredentials of(UserId userId, String lastname, String firstname, String password) {
        return new UserCredentials(userId, lastname, firstname, password);
    }

    public UserId getUserId() {
        return this.userId;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String newPassword) {
        this.password = Objects.requireNonNull(newPassword);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCredentials that = (UserCredentials) o;
        return Objects.equals(userId, that.userId) && Objects.equals(lastname, that.lastname) && Objects.equals(firstname, that.firstname) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, lastname, firstname, password);
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "userId=" + userId +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
