package fr.moleroq.al.exposition;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class UsersDTO {
    public List<UserDTO> users;
}
