package Exceptions;

public class NoLoginFoundException extends Exception{

   
    public NoLoginFoundException() {
        super("Login not found");
    }
    public NoLoginFoundException(String msg) {
        super(msg);
    }
}
