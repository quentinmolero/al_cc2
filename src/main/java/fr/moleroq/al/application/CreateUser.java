package fr.moleroq.al.application;

import fr.moleroq.al.kernel.Command;

public class CreateUser implements Command {

    public final String lastname;
    public final String firstname;
    public final String password;

    public CreateUser(String lastname, String firstname, String password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.password = password;
    }
}
