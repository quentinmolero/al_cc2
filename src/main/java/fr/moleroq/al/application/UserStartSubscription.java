package fr.moleroq.al.application;

import fr.moleroq.al.domain.User;
import fr.moleroq.al.kernel.ApplicationEvent;

import java.util.Objects;

public class UserStartSubscription implements ApplicationEvent {

    private final User user;

    public UserStartSubscription(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStartSubscription that = (UserStartSubscription) o;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}
