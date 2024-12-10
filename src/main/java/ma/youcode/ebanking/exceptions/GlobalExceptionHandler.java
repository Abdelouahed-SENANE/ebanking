package ma.youcode.ebanking.exceptions;

import ma.senane.utilities.dtos.ErrorDTO;
import ma.youcode.ebanking.exceptions.custom.PasswordUnchangedException;
import ma.youcode.ebanking.exceptions.custom.UnauthorizedActionException;
import ma.youcode.ebanking.exceptions.custom.UsernameAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

import static ma.senane.utilities.response.Response.error;

@RestControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDTO> handleBadCredentialsException(BadCredentialsException e) {
        return error(401 , e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleBadCredentialsException(UsernameNotFoundException e) {
        return error(404 , e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDTO> handleAccessDeniedException(AccessDeniedException e) {
        return error(403 , e.getMessage());
    }

    @ExceptionHandler(UnauthorizedActionException.class)
    public ResponseEntity<ErrorDTO> handleAccessDeniedException(UnauthorizedActionException e) {
        return error(401 , e.getMessage());
    }

    @ExceptionHandler(PasswordUnchangedException.class)
    public ResponseEntity<ErrorDTO> handlePasswordUnchangedException(PasswordUnchangedException e) {
        return error(409 , e.getMessage());
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException e) {
        return error(409 , e.getMessage());
    }
}
