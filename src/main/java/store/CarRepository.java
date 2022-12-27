package store;

import lombok.AllArgsConstructor;
import model.Car;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CarRepository {
    private final CrudRepository crudRepository;

    public void addCar(Car car) {
        crudRepository.run(session -> session.persist(car));
    }
}
