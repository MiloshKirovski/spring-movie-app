package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.model.exceptions.WrongEventInformation;
import mk.finki.ukim.wp.lab.service.EventBookingService;
import mk.finki.ukim.wp.lab.service.EventService;
import mk.finki.ukim.wp.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;


@Controller
@RequestMapping("/")
public class EventController {

    private final EventService eventService;
    private final EventBookingService eventBookingService;
    private final LocationService locationService;

    public EventController(EventBookingService eventBookingService, EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.eventBookingService = eventBookingService;
        this.locationService = locationService;
    }

    @GetMapping("/events")
    public String getEventsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            if (error.equals("Product_Not_Found")) {
                error = "Product was not found";
            } else if (error.equals("Wrong_Information")) {
                error = "The provided format of information is not supported!";
            }
            model.addAttribute("error",error);
        }
        model.addAttribute("events", eventService.listAll());
        model.addAttribute("locations", locationService.findAll());
        return "listEvents";
    }

    @GetMapping("/events/add")
    public String showAddEventPage(Model model) {
        model.addAttribute("locations", locationService.findAll());
        return "addEvent";
    }

    @PostMapping("/events/add")
    public String getAddEventPage(@RequestParam String eventName,
                           @RequestParam String popularityScore,
                           @RequestParam String description,
                           @RequestParam String numberTickets,
                           @RequestParam String locationId) {

        try {
            eventService.addEvent(eventName, description,
                    Double.parseDouble(popularityScore),
                    Integer.parseInt(numberTickets),
                    Long.parseLong(locationId));
        } catch (WrongEventInformation eventInformation) {
            return "redirect:/events?error=Wrong_Information";
        }
        return "redirect:/events";
    }

    @GetMapping("/events/edit-form/{eventId}")
    public String getEditEventForm(@PathVariable Long eventId, Model model) {
        if(eventService.findById(eventId).isPresent()) {
            Event event = eventService.findById(eventId).get();
            model.addAttribute("event", event);
            model.addAttribute("locations", locationService.findAll());
            return "addEvent";
        }
        return "redirect:/events?error=Product_Not_Found";
    }

    @PostMapping()
    public String filterEventSearch(@RequestParam(required = false) String text,
                                    @RequestParam(required = false) String ratingParameter,
                                    @RequestParam(required = false) String locationId,
                                    Model model){
        ArrayList<Event> events;
        if (text != null && !text.isEmpty()) {
            events = new ArrayList<>(eventService.searchEvents(text));
        } else {
            events = new ArrayList<>(eventService.listAll());
        }

        if (ratingParameter != null && !ratingParameter.trim().isEmpty()) {
            double rating = Double.parseDouble(ratingParameter);
            events.removeIf(event -> event.getPopularityScore() < rating);
        }

        if (locationId != null && !locationId.trim().isEmpty()) {
            Long locId = Long.parseLong(locationId);
            events.removeIf(event -> event.getLocation().getId() != locId);
        }
        model.addAttribute("events", events);
        model.addAttribute("locations", locationService.findAll());
        return "listEvents";
    }

    @GetMapping("/events/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        this.eventService.deleteById(id);
        return "redirect:/events";
    }
}
