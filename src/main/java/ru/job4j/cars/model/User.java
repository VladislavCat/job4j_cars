package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "auto_user")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String username;
    @NonNull
    private String password;
}
