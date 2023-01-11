package cars.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "auto_post")
@Data
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    @EqualsAndHashCode.Include
    private int id;
    @NonNull
    @Column(name = "auto_name")
    @ToString.Include
    @EqualsAndHashCode.Include
    private String name;
    @NonNull
    @ToString.Include
    private String description;
    @NonNull
    @ToString.Include
    private LocalDateTime created;
    @ManyToOne
    @JoinColumn(name = "auto_user_id")
    private User user;
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "auto_post_id")
    @ToString.Exclude
    private List<PriceHistory> priceHistoryList = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "car_id")
    @ToString.Exclude
    private Car car;
    @Column(name = "car_photo")
    @NonNull
    private byte[] photo;
    @NonNull
    private boolean status;
}
