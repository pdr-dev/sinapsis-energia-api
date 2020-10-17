package com.sinapsis.energia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Pedro Henrique
 */
public class SubestacaoNaoEncontradaException extends ResponseStatusException {

	private static final long serialVersionUID = -8662803271429640360L;

	private static final HttpStatus DEFAULT_CODE = HttpStatus.NOT_FOUND;

	public SubestacaoNaoEncontradaException() {
		super(DEFAULT_CODE, "Subestação não encontrada.");
	}

	public SubestacaoNaoEncontradaException(String message) {
		super(DEFAULT_CODE, message);
	}
}