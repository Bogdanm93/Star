package fii.practic.health.boundry.errorhandling;

import fii.practic.health.boundry.dto.ErrorDTO;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class WebRestControllerAdvice {


	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorDTO defaultHandler(Exception ex) {
		return this.generateErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex);
	}

	// This is for validation annotations
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorDTO modelValidationsHandler(ConstraintViolationException ex) {

		Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
		for (ConstraintViolation<?> constraintViolation : constraintViolations) {
			return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), constraintViolation.getMessageTemplate());
		}

		return this.generateErrorDTO(HttpStatus.BAD_REQUEST, ex);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorDTO constraintViolationHandler(MethodArgumentNotValidException ex) {
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		StringBuilder builder = new StringBuilder();
		for (FieldError error : fieldErrors) {
			builder.append(String.format("%s -> %s", error.getField(), error.getDefaultMessage()));
		}
		return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), builder.toString());
	}

	private ErrorDTO generateErrorDTO(HttpStatus httpStatus, Exception ex) {
		if(ex instanceof MethodArgumentTypeMismatchException)
			return new ErrorDTO(httpStatus.value(), "Ati introdus o valoare gresita! Numere sau doar cifre");
		return new ErrorDTO(httpStatus.value(), ex.getMessage());
	}

}
