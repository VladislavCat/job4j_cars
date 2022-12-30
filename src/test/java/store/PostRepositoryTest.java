package store;

import model.Post;
import org.assertj.core.api.Assertions;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class PostRepositoryTest {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    @Test
    public void whenAddNewPost() {
        CrudRepository crudRepository = new CrudRepository(sf);
        PostRepository repository = new PostRepository(crudRepository);
        Post post = new Post("Mercedes", "description_post", LocalDateTime.now(), new byte[]{});
        repository.addPost(post);
        Assertions.assertThat(post).isEqualTo(repository.findById(post.getId()).get());
    }

    @Test
    public void whenFindPostLastDay() {
        CrudRepository crudRepository = new CrudRepository(sf);
        PostRepository repository = new PostRepository(crudRepository);
        Post post1 = new Post("Mercedes", "description_post", LocalDateTime.now(), new byte[]{1, 2, 3});
        repository.addPost(post1);
        Post post2 = new Post("BMW", "description_post", LocalDateTime.now().minusHours(23), new byte[]{});
        repository.addPost(post2);
        Post post3 = new Post("AUDI", "description_post", LocalDateTime.now().minusDays(3), new byte[]{});
        repository.addPost(post3);
        Assertions.assertThat(repository.findPostForLastDay()).isEqualTo(List.of(post1, post2));
    }

    @Test
    public void whenFindPostWithPhoto() {
        CrudRepository crudRepository = new CrudRepository(sf);
        PostRepository repository = new PostRepository(crudRepository);
        Post post1 = new Post("Mercedes", "description_post", LocalDateTime.now(), new byte[]{1, 2, 3});
        repository.addPost(post1);
        Post post2 = new Post("BMW", "description_post", LocalDateTime.now().minusHours(23), new byte[]{});
        repository.addPost(post2);
        Post post3 = new Post("AUDI", "description_post", LocalDateTime.now().minusDays(3), new byte[]{11, 12});
        repository.addPost(post3);
        Assertions.assertThat(repository.findPostWithPhoto()).isEqualTo(List.of(post1, post3));
    }

    @Test
    public void withFindPostWithSpecificBrand() {
        CrudRepository crudRepository = new CrudRepository(sf);
        PostRepository repository = new PostRepository(crudRepository);
        Post post1 = new Post("Mercedes", "description_post", LocalDateTime.now(), new byte[]{1, 2, 3});
        repository.addPost(post1);
        Post post2 = new Post("BMW", "description_post", LocalDateTime.now().minusHours(23), new byte[]{});
        repository.addPost(post2);
        Post post3 = new Post("AUDI", "description_post", LocalDateTime.now().minusDays(3), new byte[]{11, 12});
        repository.addPost(post3);
        Assertions.assertThat(repository.findPostSpecificBrand("AUDI")).isEqualTo(List.of(post3));
    }

}
