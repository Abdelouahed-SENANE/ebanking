package ma.youcode.ebanking.exceptions.custom;

public class PasswordUnchangedException extends RuntimeException {
    public PasswordUnchangedException(String message) {
        super(message);
    }
}
