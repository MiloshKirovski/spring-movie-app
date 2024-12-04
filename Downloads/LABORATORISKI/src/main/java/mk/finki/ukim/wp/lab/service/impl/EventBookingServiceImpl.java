package mk.finki.ukim.wp.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.wp.lab.Repository.jpa.EventBookingRepositoryJpa;
import mk.finki.ukim.wp.lab.model.EventBooking;
import mk.finki.ukim.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

@Service
public class EventBookingServiceImpl implements EventBookingService {

    private final EventBookingRepositoryJpa eventBookingRepository;

    public EventBookingServiceImpl(EventBookingRepositoryJpa eventBookingRepository) {
        this.eventBookingRepository = eventBookingRepository;
    }

    @Override
    @Transactional
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        EventBooking eventBooking = new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);
        eventBookingRepository.delete(eventBooking);
        return eventBookingRepository.save(eventBooking);
    }
}
