package com.kabz.scheeels.infrastructure;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ControllerAdvice
@ResponseStatus
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Map<Class<?>, HttpStatus> httpStatuses = new HashMap<>();

    static {
        httpStatuses.put(ProductNotFoundException.class, HttpStatus.NOT_FOUND);
    }

    @Autowired
    private Gson gson;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> genericException(Exception e, WebRequest request){

        var status = Optional.ofNullable(httpStatuses.get(e.getClass())).orElse(HttpStatus.BAD_REQUEST);
        var errorMsg = new ErrorMessage(status, e.getMessage());
        log.error(gson.toJson(errorMsg));

        return ResponseEntity.status(errorMsg.status()).body(errorMsg);
    }

}
