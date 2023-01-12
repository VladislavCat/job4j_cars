package ru.job4j.cars.service;
import lombok.AllArgsConstructor;
import ru.job4j.cars.model.User;
import org.springframework.stereotype.Service;
import ru.job4j.cars.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userStore;

    public User add(User user) {
        return userStore.create(user);
    }

    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        return userStore.findUserByUsernameAndPassword(username, password);
    }

    public Optional<User> findById(int id) {
        return userStore.findById(id);
    }

    public void update(User user) {
        userStore.update(user);
    }
}
