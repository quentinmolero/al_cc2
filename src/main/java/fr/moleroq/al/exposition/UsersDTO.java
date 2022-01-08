package fr.moleroq.al.exposition;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

@XmlRootElement
final class UsersDTO {
    public List<UserDTO> users;

    @Override
    public String toString() {
        return "UsersDTO{" +
                "users=" + users +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersDTO usersDTO = (UsersDTO) o;
        return Objects.equals(users, usersDTO.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }
}
