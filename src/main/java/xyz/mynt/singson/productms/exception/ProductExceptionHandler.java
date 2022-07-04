package xyz.mynt.singson.productms.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


import xyz.mynt.singson.productms.DTO.ErrorDTO;


@ControllerAdvice
public class ProductExceptionHandler {
	
	@Autowired
	ErrorDTO errorDTO;
	
	@ExceptionHandler(ProductmsException.class)
	public ResponseEntity<ErrorDTO> productmsExceptionHandler(ProductmsException error, WebRequest request) {

//		productLogService.sendLog("NullPointerException occurred: " + error.getLocalizedMessage());
		errorDTO = errorDTO.builder()
				.withTitle("Null object")
				.withDetails(error.getMessage())
				.withErrorType(ProductmsException.class.getSimpleName())
				.withErrorCode("P500")
				.build();

		return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDTO> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException error) {
		
//		FieldError fieldError = error.getFieldError();
		
		errorDTO = errorDTO.builder()
				.withTitle("Invalid Field")
				.withDetails(error.getMessage())
				.withErrorType(MethodArgumentNotValidException.class.getSimpleName())
				.withErrorCode("P400")
				.build();
		
		return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorDTO> productAPINullPointerExceptionHandler(NullPointerException error, WebRequest request) {


		errorDTO = errorDTO.builder()
				.withTitle("Null object")
				.withDetails(error.getMessage())
				.withErrorType(NullPointerException.class.getSimpleName())
				.withErrorCode("P500")
				.build();

		return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
