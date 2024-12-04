package mk.finki.ukim.wp.lab.Repository.inmemory;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventBookingRepository {
    public List<EventBooking> findAll() {
        return DataHolder.eventBookings;
    }

    public EventBooking saveOrUpdate(EventBooking eventBooking) {
        DataHolder.eventBookings.removeIf(r -> r.getEventName().equalsIgnoreCase(eventBooking.getEventName()) &&
                r.getAttendeeName().equalsIgnoreCase(eventBooking.getAttendeeName()) &&
                r.getAttendeeAddress().equals(eventBooking.getAttendeeAddress()));
        DataHolder.eventBookings.add(eventBooking);
        return eventBooking;
    }
}
