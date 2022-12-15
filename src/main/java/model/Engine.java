package model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "engines")
@NoArgsConstructor
@RequiredArgsConstructor
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
}
