package cars.service;

import lombok.AllArgsConstructor;
import cars.model.Post;
import org.springframework.stereotype.Service;
import cars.store.PostRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository store;

    public void addPost(Post post) {
        store.addPost(post);
    }

    public List<Post> findLastDay() {
        return store.findPostForLastDay();
    }

    public List<Post> findAll() {
        return store.findAll();
    }

    public Post findById(int id) {
        return store.findById(id).get();
    }
}
