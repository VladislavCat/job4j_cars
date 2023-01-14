package ru.job4j.cars.service;

import ru.job4j.cars.model.User;

import java.util.Optional;

public interface UserServiceInterface {
    User add(User user);
    void update(User user);
    Optional<User> findById(int id);
    Optional<User> findUserByUsernameAndPassword(String name, String password);
}
