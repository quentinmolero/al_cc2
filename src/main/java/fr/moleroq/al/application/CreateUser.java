package fr.moleroq.al.application;

import fr.moleroq.al.kernel.Command;

import java.util.Objects;

public final class CreateUser implements Command {

    public final String lastname;
    public final String firstname;
    public final String password;

    public CreateUser(String lastname, String firstname, String password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.password = password;
    }

    @Override
    public String toString() {
        return "CreateUser{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateUser that = (CreateUser) o;
        return Objects.equals(lastname, that.lastname) && Objects.equals(firstname, that.firstname) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastname, firstname, password);
    }
}
