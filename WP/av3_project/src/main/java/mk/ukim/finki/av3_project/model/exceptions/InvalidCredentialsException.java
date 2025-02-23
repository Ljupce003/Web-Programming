package mk.ukim.finki.av3_project.model.exceptions;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException() {
        super("Invalid Credentials");
    }
}
