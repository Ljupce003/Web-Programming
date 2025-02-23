package mk.ukim.finki.av3_project.model.exceptions;

public class PasswordMismatchException extends RuntimeException{
    public PasswordMismatchException() {
        super("Password Mismatch");
    }
}
