package cars.store;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class DriverRepository {
    private final CrudRepository crudRepository;
}
