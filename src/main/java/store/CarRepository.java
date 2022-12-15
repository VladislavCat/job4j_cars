package store;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CarRepository {
    private final CrudRepository crudRepository;
}
