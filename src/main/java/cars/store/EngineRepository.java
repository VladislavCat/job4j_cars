package cars.store;

import lombok.AllArgsConstructor;
import cars.model.Engine;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class EngineRepository {
    private final CrudRepository crudRepository;

    public void addEngine(Engine engine) {
        crudRepository.run(session -> session.persist(engine));
    }
}
