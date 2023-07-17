package com.job.todo.Exception;

import com.job.todo.Response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SameTaskHandle extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SameTaskException.class)
    public ResponseEntity<MessageResponse> handleMaxSizeException() {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse("The task is already been created!"));
    }
}
