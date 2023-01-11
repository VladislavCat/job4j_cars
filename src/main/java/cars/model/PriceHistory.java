package cars.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "PRICE_HISTORY")
@NoArgsConstructor
@RequiredArgsConstructor
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    @NonNull
    private int before;
    @NonNull
    private int after;
    private LocalDateTime created;
}
