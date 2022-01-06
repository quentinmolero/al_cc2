package fr.moleroq.al.exposition;

import fr.moleroq.al.domain.UserId;

public class UserDTO {

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
}
