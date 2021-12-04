package fr.moleroq.al.infrastructure;

import fr.moleroq.al.domain.User;
import fr.moleroq.al.domain.UserId;
import fr.moleroq.al.domain.UserRepository;

import java.util.List;

public final class JdbcUserRepository implements UserRepository {

    @Override
    public void save(User user) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public User byId(UserId userId) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public UserId nextIdentity() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
