package mk.finki.ukim.wp.lab.model.exceptions;

public class WrongEventInformation extends RuntimeException {
    public WrongEventInformation() {
        super("You need to enter the correct event information!");
    }
}
