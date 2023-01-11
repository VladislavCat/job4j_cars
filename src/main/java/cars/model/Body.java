package cars.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity(name = "bodies")
@NoArgsConstructor
@RequiredArgsConstructor
public class Body {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
}
