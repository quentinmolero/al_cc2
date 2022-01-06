package fr.moleroq.al.application;

import fr.moleroq.al.domain.User;
import fr.moleroq.al.domain.UserRepository;
import fr.moleroq.al.exposition.UserDTO;
import fr.moleroq.al.kernel.QueryHandler;

public class RetrieveUserHandler implements QueryHandler<RetrieveUser, UserDTO> {

    private final UserRepository userRepository;

    public RetrieveUserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO handle(RetrieveUser query) {
        User user = userRepository.byId(query.userId);
        return new UserDTO(user.getUserId(), user.getUserLastname(), user.getUserFirstname());
    }
}
