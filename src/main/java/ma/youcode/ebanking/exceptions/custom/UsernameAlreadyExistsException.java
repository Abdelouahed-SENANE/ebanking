package ma.youcode.ebanking.exceptions.custom;

public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException(String username) {
        super("Username " + username + " already exists");
    }
}
