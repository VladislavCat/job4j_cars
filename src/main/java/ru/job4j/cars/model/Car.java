package ru.job4j.cars.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cars")
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "body_id")
    private Body body;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
