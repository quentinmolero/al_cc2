package fr.moleroq.al.application;

import fr.moleroq.al.domain.User;
import fr.moleroq.al.domain.UserRepository;
import fr.moleroq.al.exposition.UserDTO;
import fr.moleroq.al.kernel.QueryHandler;

import java.util.Objects;

public final class RetrieveUserHandler implements QueryHandler<RetrieveUser, UserDTO> {

    private final UserRepository userRepository;

    public RetrieveUserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO handle(RetrieveUser query) {
        User user = userRepository.byId(query.userId);
        return new UserDTO(user.getUserId(), user.getUserLastname(), user.getUserFirstname());
    }

    @Override
    public String toString() {
        return "RetrieveUserHandler{" +
                "userRepository=" + userRepository +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetrieveUserHandler that = (RetrieveUserHandler) o;
        return Objects.equals(userRepository, that.userRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRepository);
    }
}
