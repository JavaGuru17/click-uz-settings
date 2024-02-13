package uz.pdp.clickuzusers.exception;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message){
        super(message + " is already exists");
    }
}
