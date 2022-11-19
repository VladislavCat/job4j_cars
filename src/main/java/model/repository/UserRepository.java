package model.repository;

import lombok.AllArgsConstructor;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
        return user;
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.createQuery("Update User Set username = :fName,  password = :fPassword Where id = :fId")
                    .setParameter("fName", user.getUsername())
                    .setParameter("fPassword", user.getPassword())
                    .setParameter("fId", user.getId())
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    public void delete(int userId) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.createQuery("delete User where id = :fId")
                    .setParameter("fId", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        List<User> rsl = new ArrayList<>();
        try (Session session = sf.openSession()) {
            Query query = session.createQuery("from User");
            for (Object st : query.list()) {
                rsl.add((User) st);
            }
        }
        return rsl;
    }

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    public Optional<User> findById(int id) {
        Optional rsl = Optional.empty();
        try (Session session = sf.openSession()) {
            Query query = session.createQuery("from User where id = :fId").setParameter("fId", id);
            rsl = (query.uniqueResultOptional());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        List<User> rsl = new ArrayList<>();
        try (Session session = sf.openSession()) {
            Query query = session.createQuery("from User as u where u.username like :fKey").setParameter("fKey", key);
            for (Object st : query.list()) {
                rsl.add((User) st);
            }
        }
        return rsl;
    }
    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        Optional<User> rsl = Optional.empty();
        try (Session session = sf.openSession()) {
            Query query = session.createQuery("from User where username = :fUsername").setParameter("fUsername", login);
            rsl = Optional.of((User) query.list().get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
