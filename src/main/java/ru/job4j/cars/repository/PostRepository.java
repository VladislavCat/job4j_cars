package ru.job4j.cars.repository;

import ru.job4j.cars.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    void addPost(Post post);
    List<Post> findAll();
    List<Post> findPostForLastDay();
    List<Post> findPostWithPhoto();
    List<Post> findPostSpecificBrand(String brand);
    Optional<Post> findById(int id);
}
