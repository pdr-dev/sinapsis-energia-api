package com.sinapsis.energia.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sinapsis.energia.model.Subestacao;
import com.sinapsis.energia.service.impl.SubestacaoServiceImpl;

/**
 * @author Pedro Henrique
 */

@CrossOrigin
@RestController
@RequestMapping("subestacoes")
public class SubestacaoController {

	private SubestacaoServiceImpl subestacaoService;

	@Autowired
	public SubestacaoController(SubestacaoServiceImpl subestacaoService) {
		this.subestacaoService = subestacaoService;
	}

	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(this.subestacaoService.listar());
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPorCodigo(@PathVariable String codigo) {
		return ResponseEntity.status(HttpStatus.OK).body(this.subestacaoService.buscarPorCodigo(codigo));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> incluir(@Valid @RequestBody Subestacao subestacao) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.subestacaoService.incluir(subestacao));
	}

	@PutMapping("/{idSubestacao}")
	public ResponseEntity<?> alterar(@PathVariable Long idSubestacao, @Valid @RequestBody Subestacao subestacao) {
		return ResponseEntity.status(HttpStatus.OK).body(this.subestacaoService.alterar(idSubestacao, subestacao));
	}

	@DeleteMapping("/{idSubestacao}")
	public void excluir(@PathVariable Long idSubestacao) {
		this.subestacaoService.excluir(idSubestacao);
	}
}
