package ru.job4j.cars.repository;

import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User create(User user);
    void update(User user);
    void delete(int userId);
    Optional<User> findById(int id);
    Optional<User> findByLogin(String login);
    Optional<User> findUserByUsernameAndPassword(String name, String password);
}
