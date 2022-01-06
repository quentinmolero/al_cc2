package fr.moleroq.al.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserRequest {

    @NotNull
    @NotBlank
    public String lastname;

    @NotNull
    @NotBlank
    public String firstname;

    @NotNull
    @NotBlank
    public String password;
}
