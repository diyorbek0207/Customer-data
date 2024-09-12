package dasturlash.uz.controller;

import dasturlash.uz.exception.AppBadRequestException;
import dasturlash.uz.exception.ItemNotFoundException;
import jakarta.persistence.Column;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler({ItemNotFoundException.class, AppBadRequestException.class})
    public ResponseEntity<String> handler(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handlerRuntime(RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<String> handlerRuntime(InvalidDataAccessApiUsageException e) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

}
