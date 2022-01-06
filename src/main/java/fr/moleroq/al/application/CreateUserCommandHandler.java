package fr.moleroq.al.application;

import fr.moleroq.al.domain.User;
import fr.moleroq.al.domain.UserId;
import fr.moleroq.al.domain.UserRepository;
import fr.moleroq.al.kernel.CommandHandler;
import fr.moleroq.al.kernel.Event;
import fr.moleroq.al.kernel.EventDispatcher;

public class CreateUserCommandHandler implements CommandHandler<CreateUser, UserId> {

    private final UserRepository userRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public CreateUserCommandHandler(UserRepository userRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.userRepository = userRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public UserId handle(CreateUser createUser) {
        final UserId userId = userRepository.nextIdentity();
        User user = User.of(userId, createUser.lastname, createUser.firstname, createUser.password);
        userRepository.save(user);
        eventEventDispatcher.dispatch(new UserStartSubscription(user));
        return userId;
    }
}
