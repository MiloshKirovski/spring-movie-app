package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();

    List<Event> searchEvents(String text);

    boolean bookTicket(String event, int numTicket);
    Event addEvent(String eventName, String eventDescription, Double popularityScore, int numberTickets, Long locationId);
    Event save(Event event);
    Optional<Event> findById(Long id);
    void deleteById(Long id);
}
