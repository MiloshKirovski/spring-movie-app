package mk.finki.ukim.wp.lab.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super(String.format("Username with username %s already exists", username));
    }

}
