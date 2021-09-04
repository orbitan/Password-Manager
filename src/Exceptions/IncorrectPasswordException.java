package Exceptions;

public class IncorrectPasswordException extends Exception{

    public IncorrectPasswordException() {
        super("The given password was incorrect");
    }
}
