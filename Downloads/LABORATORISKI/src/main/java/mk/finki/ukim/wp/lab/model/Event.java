package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private double popularityScore;

    private int ticketLeft;

    @ManyToOne
    private Location location;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList;

    public Event(String name, String description, double popularityScore, int ticketLeft, Location location) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.ticketLeft = ticketLeft;
        this.location = location;
        this.commentList = new ArrayList<>();
    }
    public Event(String name, String description, double popularityScore, int ticketLeft, Location location, List<Comment> commentList) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.ticketLeft = ticketLeft;
        this.location = location;
        this.commentList = commentList;
    }
    public Event() {}
}
