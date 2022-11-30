package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "auto_user")
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private Date date;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "auto_post_id")
    private List<PriceHistory> priceHistoryList;
}
