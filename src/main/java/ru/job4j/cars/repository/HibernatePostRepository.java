package ru.job4j.cars.repository;

import ru.job4j.cars.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernatePostRepository implements PostRepository {
    private final CrudRepository crudRepository;

    public void addPost(Post post) {
        crudRepository.run(session -> session.persist(post));
    }

    public List<Post> findAll() {
        return crudRepository.query("from Post", Post.class);
    }

    public List<Post> findPostForLastDay() {
        LocalDateTime ldl = LocalDateTime.now().minusDays(1);
        return crudRepository.query("from Post p where p.created >= :fDate",
                Post.class, Map.of("fDate", ldl));
    }

    public List<Post> findPostWithPhoto() {
        return crudRepository.query("from Post p where p.photo != :fBytea", Post.class, Map.of("fBytea", new byte[]{}));
    }

    public List<Post> findPostSpecificBrand(String brand) {
        return crudRepository.query("from Post p where p.name = :fBrand",
                Post.class, Map.of("fBrand", brand));
    }

    public Optional<Post> findById(int id) {
        return crudRepository.optional("from Post p where p.id = :fId", Post.class, Map.of("fId", id));
    }
}
