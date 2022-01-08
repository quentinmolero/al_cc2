package fr.moleroq.al.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

final class UserRequest {

    @NotNull
    @NotBlank
    public String lastname;

    @NotNull
    @NotBlank
    public String firstname;

    @NotNull
    @NotBlank
    public String password;

    @Override
    public String toString() {
        return "UserRequest{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequest that = (UserRequest) o;
        return Objects.equals(lastname, that.lastname) && Objects.equals(firstname, that.firstname) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastname, firstname, password);
    }
}
