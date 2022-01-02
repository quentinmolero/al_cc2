package fr.moleroq.al.application;

import fr.moleroq.al.domain.User;
import fr.moleroq.al.domain.UserId;
import fr.moleroq.al.domain.UserRepository;
import fr.moleroq.al.kernel.ApplicationEvent;
import fr.moleroq.al.kernel.EventBus;

import java.util.List;

public final class UserService {

    private final UserRepository userRepository;
    private final EventBus<ApplicationEvent> eventBus;

    public UserService(UserRepository userRepository, EventBus<ApplicationEvent> eventBus) {
        this.userRepository = userRepository;
        this.eventBus = eventBus;
    }

    public void create(User user) {
        this.userRepository.save(user);
        this.eventBus.publish(new UserStartSubscription(user));
    }

    public void changePassword(UserId userId, String newPassword) {
        var user = this.userRepository.byId(userId);
        user.changePassword(newPassword);
        this.userRepository.save(user);
    }

    public List<User> all() {
        return List.copyOf(this.userRepository.findAll());
    }
}
