package store;

import lombok.AllArgsConstructor;
import model.Post;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PostRepository {
    private final CrudRepository crudRepository;
    private final static long MILLISECONDSINDAY = 86400000;

    public void addPost(Post post) {
        crudRepository.run(session -> session.persist(post));
    }

    public List<Post> findPostForLastDay() {
        LocalDateTime ldl = LocalDateTime.now().minusDays(1);
        return crudRepository.query("from Post p where p.created >= :fDate",
                Post.class, Map.of("fDate", ldl));
    }

    public List<Post> findPostWithPhoto() {
        return crudRepository.query("from Post p where p.photo != null", Post.class);
    }

    public List<Post> findPostSpecificBrand(String brand) {
        return crudRepository.query("from Post p where p.name = :fBrand",
                Post.class, Map.of("fBrand", brand));
    }
}
