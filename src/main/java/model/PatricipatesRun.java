package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

public class PatricipatesRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            User user = new User();
            user.setUsername("ADMIN");
            user.setPassword("password");
            create(user, sf);
            Post post = new Post();
            post.setName("Audi A7");
            post.setDescription("---");
            post.setCreated(new Date());
            post.setPriceHistoryList(List.of(
                    new PriceHistory(13000, 11000),
                    new PriceHistory(11000, 9000)
            ));
            post.setUser(user);
            create(post, sf);
            sf.openSession()
                    .createQuery("from Post where id = :fId", Post.class)
                    .setParameter("fId", post.getId())
                    .getSingleResult()
                    .getParticipates()
                    .forEach(System.out::println);
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static <T> T create(T model, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.persist(model);
        session.getTransaction().commit();
        session.close();
        return model;
    }

    public static void update(Post item, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(Integer id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Post item = new Post();
        item.setId(id);
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }

    public static List<Post> findAll(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Post> result = session.createQuery("from Post", Post.class).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static Post findById(Integer id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Post result = session.get(Post.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }
}