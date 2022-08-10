package example.practice.jpa.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    protected HttpStatus handleException(RuntimeException re) {
        return HttpStatus.BAD_REQUEST;
    }

}
