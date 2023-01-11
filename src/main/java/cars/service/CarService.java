package cars.service;

import lombok.RequiredArgsConstructor;
import cars.model.Car;
import org.springframework.stereotype.Service;
import cars.store.CarRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<Car> findAll() {
        return carRepository.findAll();
    }
}
