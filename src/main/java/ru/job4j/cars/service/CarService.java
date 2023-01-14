package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import ru.job4j.cars.model.Car;
import org.springframework.stereotype.Service;
import ru.job4j.cars.repository.CarRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService implements CarServiceInterface {
    private final CarRepository carRepository;

    public List<Car> findAll() {
        return carRepository.findAll();
    }
}
