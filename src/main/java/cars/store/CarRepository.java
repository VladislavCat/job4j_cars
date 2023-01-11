package cars.store;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import cars.model.Car;
import java.util.List;

@Repository
@AllArgsConstructor
public class CarRepository {
    private final CrudRepository crudRepository;

    public List<Car> findAll() {
        return crudRepository.query("from Car", Car.class);
    }

    public void addCar(Car car) {
        crudRepository.run(session -> session.persist(car));
    }
}
