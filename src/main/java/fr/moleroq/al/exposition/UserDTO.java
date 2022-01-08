package fr.moleroq.al.exposition;

import fr.moleroq.al.domain.UserId;

import java.util.Objects;

public final class UserDTO {

    public final UserId id;
    public final String lastname;
    public final String firstname;

    public UserDTO(UserId id, String lastname, String firstname) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(lastname, userDTO.lastname) && Objects.equals(firstname, userDTO.firstname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastname, firstname);
    }
}
