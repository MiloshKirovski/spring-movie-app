package mk.finki.ukim.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.wp.lab.model.exceptions.NoTicketsLeftException;
import mk.finki.ukim.wp.lab.service.EventBookingService;
import mk.finki.ukim.wp.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {

    private final EventBookingService eventBookingService;
    private final EventService eventService;

    public EventBookingController(EventBookingService eventBookingService, EventService eventService) {
        this.eventBookingService = eventBookingService;
        this.eventService = eventService;
    }

    @PostMapping()
    public String getInfo(@RequestParam String eventName,
                          @RequestParam String attendeeName,
                          HttpServletRequest request,
                          @RequestParam String numberTickets,
                          Model model) {
        String attendeeAddress = request.getRemoteAddr();

        eventBookingService.placeBooking(eventName, attendeeName, attendeeAddress, Integer.parseInt(numberTickets));

        model.addAttribute("attendeeName", attendeeName);
        model.addAttribute("attendeeAddress", attendeeAddress);
        model.addAttribute("eventName", eventName);
        model.addAttribute("numberOfTickets", numberTickets);

        try {
            eventService.bookTicket(eventName, Integer.parseInt(numberTickets));
            model.addAttribute("booked", true);
        } catch (NoTicketsLeftException exception) {
            model.addAttribute("booked", false);
            model.addAttribute("message", exception.getMessage());
            return "bookingConfirmation";
        }
        return "bookingConfirmation";
    }
}


// za sekoj event da moze da se ostavi komentar, ushte edno kopce da ima shto ke te nosi na see details i site komentari