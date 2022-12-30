package model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "auto_post")
@Data
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private int id;
    @NonNull
    @Column(name = "auto_name")
    @ToString.Include
    private String name;
    @NonNull
    @ToString.Include
    private String description;
    @NonNull
    @ToString.Include
    private LocalDateTime created;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "auto_user_id")
    private User user;
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "auto_post_id")
    @ToString.Exclude
    private List<PriceHistory> priceHistoryList = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "participates",
            joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    @ToString.Exclude
    private List<User> participates = new ArrayList<>();
    @Column(name = "car_photo")
    @NonNull
    private byte[] photo;
}
