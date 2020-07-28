package com.ucentral.microservicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger log;

	public RestExceptionHandler() {
		super();
		log = LogManager.getLogger(RestExceptionHandler.class);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		BindingResult bindingResult = ex.getBindingResult();

		List<String> errores = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {

			errores.add(String.format("@%s:%s,", fieldError.getField(), fieldError.getDefaultMessage()));
		}

		ApiError apiError = new ApiError(status, "Ha ocurrido un error al tratar de validar la entidad", errores,
				new Date());

		return new ResponseEntity<>(apiError, headers, apiError.getStatus());
	}

	/**
	 * Controla todas las excepciones inesperadas que arroje el sistema
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
				"Ha ocurrido un error inesperado, porfavor contacte con el administrador del sistema", new Date());
		log.error("Error inesperado", ex);
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

}
