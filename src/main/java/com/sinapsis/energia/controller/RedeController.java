package com.sinapsis.energia.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.server.ResponseStatusException;

import com.sinapsis.energia.model.Rede;
import com.sinapsis.energia.model.Subestacao;
import com.sinapsis.energia.repository.RedeRepository;
import com.sinapsis.energia.repository.SubestacaoRepository;

/**
 * @author Pedro Henrique
 */

@CrossOrigin
@RestController
@RequestMapping(name = "rede")
public class RedeController {

	@Autowired
	private RedeRepository redeRepository;

	@Autowired
	private SubestacaoRepository subestacaoRepository;

	@GetMapping
	public List<Rede> listar() {
		return redeRepository.findAll();
	}

	@GetMapping
	public ResponseEntity<Rede> buscarPorCodigoDaRede(@PathVariable String codigo) {
		Optional<Rede> rede = redeRepository.findByCodigo(codigo);

		if (rede.isPresent()) {
			return ResponseEntity.ok(rede.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public List<Rede> buscarPorSubestacao(@Valid @RequestBody Subestacao subestacao) {
		List<Rede> redesPorSubestacao = redeRepository.findBySubestacao(subestacao);

		if (redesPorSubestacao.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Não foi encontrado nenhuma Rede nessa Subestação!");
		} else {
			return redesPorSubestacao;
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Rede incluir(@Valid @RequestBody Rede rede) {
		Optional<Rede> redeExistente = redeRepository.findByCodigo(rede.getCodigo());

		if (redeExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma Rede com esse código!");
		} else {
			Optional<Subestacao> subestacaoExistente = subestacaoRepository
					.findByCodigo(rede.getSubestacao().getCodigo());
			if (subestacaoExistente.isPresent()) {
				return redeRepository.save(rede);
			} else {
				Subestacao subestacao = subestacaoRepository.save(rede.getSubestacao());
				rede.setSubestacao(subestacao);
				return redeRepository.save(rede);
			}
		}
	}

	@PutMapping
	public ResponseEntity<Rede> alterar(@PathVariable Long idRede, @Valid @RequestBody Rede rede) {
		Optional<Rede> redeExistente = redeRepository.findById(idRede);

		if (redeExistente.isPresent()) {
			Optional<Subestacao> subestacaoExistente = subestacaoRepository
					.findByCodigo(rede.getSubestacao().getCodigo());
			if (!subestacaoExistente.isPresent()) {
				Subestacao subestacao = subestacaoRepository.save(rede.getSubestacao());
				rede.setSubestacao(subestacao);
			}
			rede.setIdRedeMT(idRede);
			redeRepository.save(rede);
			return ResponseEntity.ok(rede);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping
	public void excluir(@PathVariable Long idRede) {
		redeRepository.deleteById(idRede);
	}

}
