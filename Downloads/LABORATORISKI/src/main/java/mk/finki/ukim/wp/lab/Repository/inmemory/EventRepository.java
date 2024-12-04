package mk.finki.ukim.wp.lab.Repository.inmemory;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.model.Location;
import mk.finki.ukim.wp.lab.model.exceptions.NoTicketsLeftException;
import mk.finki.ukim.wp.lab.model.exceptions.WrongEventInformation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {

    private final LocationRepository locationRepository;

    public EventRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Event> findAll() {
        return DataHolder.events;
    }

    public List<Event> searchEvents(String text) {
        return DataHolder.events.stream().filter(r -> r.getName().toLowerCase().contains(text.toLowerCase()) ||
                r.getDescription().toLowerCase().contains(text.toLowerCase())).toList();
    }

    public boolean bookTicket(String event, int numTickets) {
        Optional<Event> found = DataHolder.events.stream().filter(r -> r.getName().equalsIgnoreCase(event)).findFirst();
        if (found.isPresent()) {
            int ticketsLeft = found.get().getTicketLeft();
            if (ticketsLeft - numTickets < 0) {
                throw new NoTicketsLeftException();
            } else {
                Event newEvent = found.get();
                newEvent.setTicketLeft(found.get().getTicketLeft() - numTickets);
                DataHolder.events.removeIf(r -> r.getName().equals(newEvent.getName()));
                DataHolder.events.add(newEvent);
                return true;
            }
        }
        return false;
    }

    public Event save(String eventName, String eventDescription, Double popularityScore, int numberTickets, Long locationId) {
        Location location;
        if (locationRepository.findLocation(locationId).isPresent()) {
            location = locationRepository.findLocation(locationId).get();
        } else {
            throw new WrongEventInformation();
        }
        DataHolder.events.removeIf(i -> i.getName().equals(eventName));
        Event event = new Event(eventName, eventDescription, popularityScore, numberTickets, location);
        DataHolder.events.add(event);

        return event;
    }

    public Event save(Event event) {
        DataHolder.events.removeIf(i -> i.getName().equals(event.getName()));
        DataHolder.events.add(event);

        return event;
    }

    public Optional<Event> findById(Long id) {
        return DataHolder.events.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public void deleteById(Long id) {
        DataHolder.events.removeIf(i -> i.getId().equals(id));
    }
}
