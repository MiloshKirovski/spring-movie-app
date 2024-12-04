package mk.finki.ukim.wp.lab.model.exceptions;

public class NoTicketsLeftException extends RuntimeException {
    public NoTicketsLeftException() {
        super("THERE ARE NO TICKETS, OF THAT AMOUNT, LEFT FOR THIS EVENT!");
    }
}
