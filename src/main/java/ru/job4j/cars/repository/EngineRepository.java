package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import ru.job4j.cars.model.Engine;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class EngineRepository {
    private final CrudRepository crudRepository;

    public void addEngine(Engine engine) {
        crudRepository.run(session -> session.persist(engine));
    }
}
