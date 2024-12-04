//package mk.finki.ukim.wp.lab.web.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.finki.ukim.wp.lab.model.Event;
//import mk.finki.ukim.wp.lab.service.EventBookingService;
//import mk.finki.ukim.wp.lab.service.EventService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//@WebServlet(name = "eventListener", value = "/*")
//public class EventListServlet extends HttpServlet {
//    private final EventService eventService;
//    private final SpringTemplateEngine springTemplateEngine;
//
//    public EventListServlet(EventBookingService eventBookingService, EventService eventService, SpringTemplateEngine springTemplateEngine) {
//        this.springTemplateEngine = springTemplateEngine;
//        this.eventService = eventService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context = new WebContext(webExchange);
//
//        context.setVariable("events", eventService.listAll());
//        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String text = req.getParameter("text");
//        String ratingParameter = req.getParameter("rating");
//
//        ArrayList<Event> events;
//
//        if (text != null && !text.isEmpty()) {
//            events = new ArrayList<>(eventService.searchEvents(text));
//        } else {
//            events = new ArrayList<>(eventService.listAll());
//        }
//
//        if (ratingParameter != null && !ratingParameter.trim().isEmpty()) {
//            double rating = Double.parseDouble(ratingParameter);
//            events.removeIf(event -> event.getPopularityScore() < rating);
//        }
//
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context = new WebContext(webExchange);
//
//        context.setVariable("events", events);
//
//        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
//    }
//}
