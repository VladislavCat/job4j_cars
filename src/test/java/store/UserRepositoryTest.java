package store;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.NoResultException;
import java.util.List;

public class UserRepositoryTest {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    private final UserRepository userRepository = new UserRepository(new CrudRepository(sf));

    @Test
    public void whenCreateUser() {
        User user = userRepository.create(new User("Grigory", "qwerty"));
        Assertions.assertThat(user).isEqualTo(userRepository.findById(user.getId()).get());
    }

    @Test
    public void whenUpdateUser() {
        User user = userRepository.create(new User("Grigory", "qwerty"));
        user.setUsername("Vladislav");
        userRepository.update(user);
        Assertions.assertThat(user).isEqualTo(userRepository.findById(user.getId()).get());
    }

    @org.junit.Test(expected = NoResultException.class)
    public void whenDeleteUser() {
        User user = userRepository.create(new User("Grigory", "qwerty"));
        userRepository.delete(user.getId());
        userRepository.findById(user.getId());
    }

    @Test
    public void whenFindOrderByIdUser() {
        User user1 = new User("Ulty", "qwerty");
        User user2 = new User("Save", "qwerty");
        User user3 = new User("Vladislav", "qwerty");
        User user4 = new User("Elem", "qwerty");
        List<User> expected = List.of(user4, user2, user3, user1);
        expected.forEach(userRepository::create);
        Assertions.assertThat(expected).isEqualTo(userRepository.findAllOrderById());
    }

    @Test
    public void whenFindLikeByLogin() {
        User user1 = userRepository.create(new User("SGI_Mt", "qwerty"));
        User user2 = userRepository.create(new User("Grigory", "qwerty"));
        User user3 = userRepository.create(new User("SGI_s", "qwerty"));
        User user4 = userRepository.create(new User("Java", "qwerty"));
        Assertions.assertThat(List.of(user1, user3)).isEqualTo(userRepository.findByLikeLogin("SGI"));
    }

    @Test
    public void whenFindByLogin() {
        User user1 = userRepository.create(new User("Grigory", "qwerty"));
        User user2 = userRepository.create(new User("Java", "qwerty"));
        Assertions.assertThat(user2).isEqualTo(userRepository.findByLogin("Java").get());
    }
}
