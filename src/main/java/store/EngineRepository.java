package store;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class EngineRepository {
    private final CrudRepository crudRepository;
}
