package com.sinapsis.energia.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sinapsis.energia.model.Subestacao;
import com.sinapsis.energia.repository.SubestacaoRepository;

@CrossOrigin
@RestController
@RequestMapping("subestacao")
public class SubestacaoController {

	@Autowired
	private SubestacaoRepository subestacaoRepository;

	@GetMapping
	public List<Subestacao> listar() {
		return subestacaoRepository.findAll();
	}

	@GetMapping
	public ResponseEntity<Subestacao> buscarPorCodigo(@PathVariable String codigo) {
		Optional<Subestacao> subestacao = subestacaoRepository.findByCodigo(codigo);
		if (subestacao.isPresent()) {
			return ResponseEntity.ok(subestacao.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Subestacao incluir(@Valid @RequestBody Subestacao subestacao) {
		Optional<Subestacao> subestacaoExistente = subestacaoRepository.findByCodigo(subestacao.getCodigo());

		if (subestacaoExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma Subestação com esse código!");
		}
		
		return subestacaoRepository.save(subestacao);
	}
}
