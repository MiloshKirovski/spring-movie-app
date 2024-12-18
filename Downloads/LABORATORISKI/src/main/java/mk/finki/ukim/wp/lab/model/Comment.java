package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.OnDelete;

@Data
@AllArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String comment;

    @ManyToOne(cascade = CascadeType.ALL)
    private Event event;

    public Comment(User user, String comment, Event event) {
        this.user = user;
        this.comment = comment;
        this.event = event;
    }

    public Comment() {}
}
