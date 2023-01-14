package ru.job4j.cars.service;

import ru.job4j.cars.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostServiceInterface {
    void addPost(Post post);
    List<Post> findAll();
    Post findById(int id);
}
