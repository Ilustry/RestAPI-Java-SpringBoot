package com.crud.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.crud.exception.ProdutoConflictException;
import com.crud.exception.ProdutoNullException;

@ControllerAdvice
public class ProdutoControllerAdvice extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ProdutoNullException.class)
	public ResponseEntity<Object> captureNullError() {

		Map<String, String> body = Map.of("message", "Verifique se os campos foram preenchidos corretamente");
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}
	
	@ExceptionHandler(ProdutoConflictException.class)
	public ResponseEntity<Object> conflictError() {

		Map<String, String> body = Map.of("message", "O produto selecionado já foi removido");
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
	}

}
