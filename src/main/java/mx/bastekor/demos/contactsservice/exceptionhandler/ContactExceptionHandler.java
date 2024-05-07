package mx.bastekor.demos.contactsservice.exceptionhandler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ContactExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail handle(MethodArgumentNotValidException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        var errors = ex.getFieldErrors()
                .stream()
                .map(fieldError -> {
                    return fieldError.getField() + " - " + fieldError.getDefaultMessage();
                })
                .toList();

        problemDetail.setProperty("my-errors", errors);

        return problemDetail;
    }
}