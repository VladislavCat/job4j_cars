package ru.job4j.cars.repository;

import ru.job4j.cars.model.Car;

import java.util.List;

public interface CarRepository {
    List<Car> findAll();
    void addCar(Car car);
}
