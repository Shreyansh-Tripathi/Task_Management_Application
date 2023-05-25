package com.service.teacher.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandle extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeExceptions(RuntimeException exception, WebRequest request){
        Map<String,Object> errorMap=new LinkedHashMap<>();

        errorMap.put("Message:", exception.getMessage());

        return new ResponseEntity<Map<String, Object>>(errorMap, HttpStatusCode.valueOf(400));
    }

}