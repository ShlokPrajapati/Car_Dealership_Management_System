package Exceptions;

public class PasswordAlreadyTakenException extends RuntimeException
{
    public PasswordAlreadyTakenException(String msg) 
    { 
        super(msg);
    }

}
