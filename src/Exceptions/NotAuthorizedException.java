package Exceptions;

public class NotAuthorizedException extends Exception{
    
    public NotAuthorizedException(){
        super("The action which called was not authorized to use");
    }
}
