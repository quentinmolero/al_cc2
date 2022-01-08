package fr.moleroq.al.exposition;

import fr.moleroq.al.application.CreateUser;
import fr.moleroq.al.application.RetrieveUser;
import fr.moleroq.al.application.RetrieveUsers;
import fr.moleroq.al.domain.UserId;
import fr.moleroq.al.kernel.CommandBus;
import fr.moleroq.al.kernel.QueryBus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
final class UserController {

    private final QueryBus usersQueryBus;
    private final QueryBus userQueryBus;
    public final CommandBus commandBus;

    public UserController(
            @Qualifier("usersQueryBus") QueryBus usersQueryBus,
            @Qualifier("userQueryBus") QueryBus userQueryBus,
            @Qualifier("userCommandBus") CommandBus commandBus) {
        this.usersQueryBus = usersQueryBus;
        this.userQueryBus = userQueryBus;
        this.commandBus = commandBus;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersDTO> getAll() {
        final List<UserDTO> users = usersQueryBus.send(new RetrieveUsers());
        UsersDTO usersDTOResult = new UsersDTO();
        usersDTOResult.users = users;
        return ResponseEntity.ok(usersDTOResult);
    }

    @GetMapping(value = "/user", params = "userId", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<UserDTO> getUser(@RequestParam(value = "userId") int userId) {
        final UserDTO userDTOResult = userQueryBus.send(new RetrieveUser(UserId.of(userId)));
        return ResponseEntity.ok(userDTOResult);
    }

    @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid UserRequest request) {
        CreateUser createUser = new CreateUser(request.lastname, request.firstname, request.password);
        UserId userId = commandBus.send(createUser);
        return ResponseEntity.created(URI.create("/users/" + userId.getValue())).build();
    }

    @Override
    public String toString() {
        return "UserController{" +
                "usersQueryBus=" + usersQueryBus +
                ", userQueryBus=" + userQueryBus +
                ", commandBus=" + commandBus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserController that = (UserController) o;
        return Objects.equals(usersQueryBus, that.usersQueryBus) && Objects.equals(userQueryBus, that.userQueryBus) && Objects.equals(commandBus, that.commandBus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersQueryBus, userQueryBus, commandBus);
    }
}
