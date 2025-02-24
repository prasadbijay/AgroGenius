package agrogenius.exception;

public class AdminNotFoundException extends RuntimeException {

    // Constructor that accepts a custom error message
    public AdminNotFoundException(String message) {
        super(message);  // Pass the message to the parent class (RuntimeException)
    }
}

