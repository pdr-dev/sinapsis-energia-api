package com.sinapsis.energia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RedeNaoEncontradaException extends ResponseStatusException {

	private static final long serialVersionUID = -8662803271429640360L;
	
	private static final HttpStatus DEFAULT_CODE = HttpStatus.NOT_FOUND;
	
	public RedeNaoEncontradaException() {
		super(DEFAULT_CODE, "Rede não encontrada.");
	}

	public RedeNaoEncontradaException(String message) {
		super(DEFAULT_CODE, message);
	}

}
