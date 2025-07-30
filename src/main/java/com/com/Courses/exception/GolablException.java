package com.com.Courses.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.com.Courses.dto.ErrorDto;

import io.jsonwebtoken.io.IOException;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GolablException {
	@ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("status", HttpStatus.UNAUTHORIZED .value());
        errorResponse.put("error", "Internal Server Error");

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	
	/*@ExceptionHandler(ProducerException.class)
	public ResponseEntity<ErrorDto>ProducerException(ProducerException ex){
	ErrorDto errorDto=new ErrorDto();
	errorDto.setError("Producer Exception for message");
	
	return new ResponseEntity(errorDto,HttpStatus.BAD_REQUEST);
		
	}*/
	
	
	
/*	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDto>handleMethodArgumentValidException(MethodArgumentNotValidException ex){
		

	    ErrorDto errorDto = new ErrorDto();

	    BindingResult bindingResult = ex.getBindingResult();
	    List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		  String message = fieldErrors.stream()
		            .findFirst()
		            .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
		            .orElse("Validation error");

		    errorDto.setError(message);

		    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
	}
	
	*/
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDto> handleMethodArgumentValidException(MethodArgumentNotValidException ex) {
	    String errorMessage = ex.getBindingResult()
	                            .getFieldErrors()
	                            .stream()
	                            .findFirst()
	                            .map(error -> error.getField() + ": " + error.getDefaultMessage())
	                            .orElse("Validation error");

	    return ResponseEntity.badRequest().body(new ErrorDto(errorMessage));
	}

	
	@ExceptionHandler(ConstraintViolationException.class)
	public  ResponseEntity<ErrorDto>handleContraintValidation(ConstraintViolationException ex)
	{
		ErrorDto errorDto=new ErrorDto();
		String errorMessage=ex.getConstraintViolations().stream().findFirst().
		map(violation-> violation.getPropertyPath()+": "+violation.getMessage()).orElse("Constraint violation occurred");
		errorDto.setError(errorMessage);
		
		return new ResponseEntity(errorDto,HttpStatus.BAD_REQUEST);
		
	}
	  @ExceptionHandler(IllegalStateException.class)
	    public ResponseEntity<ErrorDto> handleIllegalStateException(IllegalStateException ex) {
	        ErrorDto errorDto = new ErrorDto();
	        errorDto.setError("Illegal state: " + ex.getMessage());
	        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(java.io.IOException.class)
	    public ResponseEntity<ErrorDto> handleIOException(IOException ex) {
	        ErrorDto errorDto = new ErrorDto();
	        errorDto.setError("IO Error: " + ex.getMessage());
	        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}
