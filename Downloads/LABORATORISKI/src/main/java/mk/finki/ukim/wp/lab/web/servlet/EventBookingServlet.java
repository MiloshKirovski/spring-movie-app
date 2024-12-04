//package mk.finki.ukim.wp.lab.web.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.finki.ukim.wp.lab.model.Event;
//import mk.finki.ukim.wp.lab.model.EventBooking;
//import mk.finki.ukim.wp.lab.model.exceptions.NoTicketsLeftException;
//import mk.finki.ukim.wp.lab.service.EventBookingService;
//import mk.finki.ukim.wp.lab.service.EventService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//
//@WebServlet(name = "EventBookingServlet", value = "/eventBooking")
//public class EventBookingServlet extends HttpServlet {
//    private final EventService eventService;
//    private final SpringTemplateEngine springTemplateEngine;
//    private final EventBookingService eventBookingService;
//
//    public EventBookingServlet(SpringTemplateEngine springTemplateEngine, EventBookingService eventBookingService, EventService eventService) {
//        this.eventBookingService = eventBookingService;
//        this.springTemplateEngine = springTemplateEngine;
//        this.eventService = eventService;
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String eventName = req.getParameter("event");
//        String attendeeName = req.getParameter("attendeeName");
//        String attendeeAddress = req.getRemoteAddr();
//        String numberTickets = req.getParameter("numTickets");
//
//        EventBooking booking = eventBookingService.placeBooking(eventName, attendeeName, attendeeAddress, Integer.parseInt(numberTickets));
//
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context = new WebContext(webExchange);
//
//        boolean booked = true;
//        try {
//            booked = eventService.bookTicket(eventName, Integer.parseInt(numberTickets));
//            context.setVariable("booked", booked);
//        } catch (NoTicketsLeftException exception) {
//            context.setVariable("message", exception.getMessage());
//            context.setVariable("booked", false);
//        }
//        context.setVariable("attendeeName", booking.getAttendeeName());
//        context.setVariable("attendeeAddress", attendeeAddress);
//        context.setVariable("eventName", booking.getEventName());
//        context.setVariable("numberOfTickets", booking.getNumberOfTickets());
//
//        springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());
//    }
//}
//
//// za sekoj event da se cuvaat kolku ima dostapni tickets. I koga nema da ima da se pokaze nekoja poraka deka eventot
//// e sold out. Da ne moze klientot da napravi rezervacija ako nema dostapno bileti/
