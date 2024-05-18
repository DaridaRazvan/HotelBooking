package hotel.booking.backend.exceptions;

public class CustomException extends RuntimeException {

    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }
}