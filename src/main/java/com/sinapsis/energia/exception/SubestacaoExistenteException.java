package com.sinapsis.energia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Pedro Henrique
 */
public class SubestacaoExistenteException extends ResponseStatusException {

	private static final long serialVersionUID = -8662803271429640360L;

	private static final HttpStatus DEFAULT_CODE = HttpStatus.BAD_REQUEST;

	public SubestacaoExistenteException(String message) {
		super(DEFAULT_CODE, message);
	}
}
