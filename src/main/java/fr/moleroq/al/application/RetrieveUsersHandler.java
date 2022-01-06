package fr.moleroq.al.application;

import fr.moleroq.al.domain.UserRepository;
import fr.moleroq.al.domain.UserState;
import fr.moleroq.al.exposition.UserDTO;
import fr.moleroq.al.kernel.QueryHandler;

import java.util.List;
import java.util.stream.Collectors;

public class RetrieveUsersHandler implements QueryHandler<RetrieveUsers, List<UserDTO>> {

    private final UserRepository userRepository;

    public RetrieveUsersHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> handle(RetrieveUsers query) {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getHistory().getStates().contains(UserState.CREATED))
                .map(user ->
                        new UserDTO(user.getUserId(), user.getUserLastname(),
                                user.getUserFirstname()))
                .collect(Collectors.toList());
    }
}