package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class EventBooking {
    @Id
    private String eventName;

    private String attendeeName;

    private String attendeeAddress;

    private Long numberOfTickets;

    public EventBooking() {}
}
