package mk.finki.ukim.wp.lab.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException {
    public PasswordsDoNotMatchException(){
        super("Passwords do not match!");
    }
}