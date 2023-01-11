package cars.store;

import lombok.AllArgsConstructor;
import cars.model.User;
import org.springframework.stereotype.Repository;
import cars.store.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepository {
    public final CrudRepository crudRepository;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        crudRepository.run(session -> session.persist(user));
        return user;
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        crudRepository.run(session -> session.merge(user));
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    public void delete(int userId) {
        crudRepository.run("delete From User where id = :fId", Map.of("fId", userId));
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        return crudRepository.query("from User order by id", User.class);
    }
    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    public Optional<User> findById(int id) {
        return crudRepository.optional("from User where id = :fId", User.class, Map.of("fId", id));
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        return crudRepository.query("from User where username like :fKey", User.class, Map.of("fKey", "%" + key + "%"));
    }
    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        return crudRepository.optional("from User where username = :fLogin", User.class, Map.of("fLogin", login));
    }

    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        return crudRepository.optional("from User where username = :fUsername and password = :fPassword",
                User.class, Map.of("fUsername", username, "fPassword", password));
    }
}
