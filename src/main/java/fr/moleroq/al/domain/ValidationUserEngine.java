package fr.moleroq.al.domain;

import fr.moleroq.al.kernel.Engine;

import java.util.function.Predicate;

@Engine
public final class ValidationUserEngine implements Predicate<User> {

    private static final ValidationUserEngine INSTANCE = new ValidationUserEngine();

    private ValidationUserEngine() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Already initialized");
        }
    }

    public static ValidationUserEngine getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean test(User user) {
        return user != null &&
                user.getUserId() != null &&
                (user.getUserFirstname() != null && !user.getUserFirstname().isEmpty()) &&
                (user.getUserLastname() != null && !user.getUserLastname().isEmpty()) &&
                (user.getUserPassword() != null && !user.getUserPassword().isEmpty());
    }
}
