package mk.finki.ukim.wp.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.wp.lab.Repository.jpa.EventRepositoryJpa;
import mk.finki.ukim.wp.lab.Repository.jpa.LocationRepositoryJpa;
import mk.finki.ukim.wp.lab.model.Comment;
import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.model.Location;
import mk.finki.ukim.wp.lab.model.exceptions.NoTicketsLeftException;
import mk.finki.ukim.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepositoryJpa eventRepository;
    private final LocationRepositoryJpa locationRepository;

    public EventServiceImpl(EventRepositoryJpa eventRepository, LocationRepositoryJpa locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        if (text.isEmpty()) {
            throw new IllegalArgumentException("Text must be filled!");
        }
        return eventRepository.findEventByDescriptionContainingOrNameContaining(text, text);
    }

    @Override
    @Transactional
    public boolean bookTicket(String eventName, int numTicket) {
            Optional<Event> found = eventRepository.findEventByName(eventName);
            if (found.isPresent()) {
                int ticketsLeft = found.get().getTicketLeft();
                if (ticketsLeft - numTicket < 0) {
                    throw new NoTicketsLeftException();
                } else {
                    Event existingEvent = found.get();
                    existingEvent.setTicketLeft(ticketsLeft - numTicket);
//                    DataHolder.events.removeIf(r -> r.getName().equals(newEvent.getName()));
//                    DataHolder.events.add(newEvent);
                    eventRepository.save(existingEvent);

                    // OGROMNI PROBLEMI SS DELETE PA SAVE??????
                    return true;
                }
            }
        return false;
    }

    @Override
    @Transactional
    public Event addEvent(Long id, String eventName, String eventDescription, Double popularityScore, int numberTickets, Long locationId) {
        Optional<Location> location = locationRepository.findById(locationId);
        if(location.isPresent()) {
            Event event;
            if (eventRepository.findById(id).isPresent()) {
                List<Comment> commentList = new ArrayList<>();
                if (eventRepository.findById(id).isPresent()) {
                    commentList = List.copyOf(eventRepository.findById(id).get().getCommentList());
                }

                event = new Event(eventName, eventDescription, popularityScore, numberTickets, location.get(), commentList);

                for (Comment comment : commentList) {
                    comment.setEvent(event);
                }
                eventRepository.deleteById(id);
            } else {
                event = new Event(eventName, eventDescription, popularityScore, numberTickets, location.get());
            }

            return eventRepository.save(event);
        } else {
            throw new RuntimeException("LOCATION EXCEPTION");
        }
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.eventRepository.deleteById(id);
    }
}
