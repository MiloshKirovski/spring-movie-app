package mk.finki.ukim.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.wp.lab.model.Comment;
import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.service.CommentService;
import mk.finki.ukim.wp.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller()
@RequestMapping("")
public class EventCommentController {

    public final EventService eventService;
    public final CommentService commentService;

    public EventCommentController(EventService eventService, CommentService commentService) {
        this.eventService = eventService;
        this.commentService = commentService;
    }

    @GetMapping("/comments/{id}")
    public String leaveComment(@PathVariable Long id, Model model) {
        Optional<Event> optionalEvent = this.eventService.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            model.addAttribute("comments", event.getCommentList());
            model.addAttribute("eventId", id);
            return "comments";
        }
        return "redirect:/events";
    }


    @PostMapping("/comments/{id}")
    public String postComment(@RequestParam String comment, @PathVariable Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Optional<Event> optionalEvent = this.eventService.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            Comment comment1 = new Comment(user, comment, event);
            commentService.addComment(comment1);

            event.getCommentList().add(comment1);
            this.eventService.save(event);
        }
        return "redirect:/comments/" + id; // Redirect to the comments page
    }
}
