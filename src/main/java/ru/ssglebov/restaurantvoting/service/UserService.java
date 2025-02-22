package ru.ssglebov.restaurantvoting.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ssglebov.restaurantvoting.model.User;
import ru.ssglebov.restaurantvoting.repository.UserRepository;

import static ru.ssglebov.restaurantvoting.util.ValidationUtil.checkNotFound;
import static ru.ssglebov.restaurantvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(user), user.id());
    }
}
