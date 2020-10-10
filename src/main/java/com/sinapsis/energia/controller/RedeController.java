package com.sinapsis.energia.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinapsis.energia.exception.RedeNaoEncontradaException;
import com.sinapsis.energia.model.Rede;
import com.sinapsis.energia.model.Subestacao;
import com.sinapsis.energia.service.RedeService;

/**
 * @author Pedro Henrique
 */

@CrossOrigin
@RestController
@RequestMapping(path = "/rede")
public class RedeController {
	
	@Autowired
	private RedeService redeService;

	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(this.redeService.listar());
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPorCodigoDaRede(@PathVariable String codigo) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(this.redeService.buscarPorCodigoDaRede(codigo));
	}

	@GetMapping("/subestacao")
	public ResponseEntity<?> buscarPorSubestacao(@Valid @RequestBody Subestacao subestacao) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(this.redeService.buscarPorSubestacao(subestacao)
				.orElseThrow(() -> new RedeNaoEncontradaException()));
	}

	@PostMapping
	public ResponseEntity<?> incluir(@Valid @RequestBody Rede rede) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(this.redeService.incluir(rede));
	}

}
