package com.sinapsis.energia.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinapsis.energia.exception.RedeExistenteException;
import com.sinapsis.energia.exception.RedeNaoEncontradaException;
import com.sinapsis.energia.model.Rede;
import com.sinapsis.energia.model.Subestacao;
import com.sinapsis.energia.repository.RedeRepository;
import com.sinapsis.energia.repository.SubestacaoRepository;

@Service
public class RedeService {

	private RedeRepository redeRepository;
	private SubestacaoRepository subestacaoRepository;

	@Autowired
	public RedeService(RedeRepository redeRepository, SubestacaoRepository subestacaoRepository) {
		this.redeRepository = redeRepository;
		this.subestacaoRepository = subestacaoRepository;
	}

	public List<Rede> listar() {
		return this.redeRepository.findAll();
	}

	public Optional<Rede> buscarPorCodigoDaRede(String codigo) {
		Optional<Rede> rede = this.redeRepository.findByCodigo(codigo);
		if (!rede.isPresent())
			throw new RedeNaoEncontradaException();
		return rede;
		
	}

	public Optional<List<Rede>> buscarPorSubestacao(@Valid Subestacao subestacao) {
		List<Rede> redes = this.redeRepository.findBySubestacao(subestacao);
		if (redes.isEmpty())
			return Optional.empty();
		return Optional.ofNullable(redes);
	}

	public Optional<@Valid Rede> incluir(@Valid Rede rede) {
		Optional<Rede> redeExistente = this.redeRepository.findByCodigo(rede.getCodigo());

		if (redeExistente.isPresent()) {
			throw new RedeExistenteException("Já existe uma Rede com esse código!");
		} else {
			Optional<Subestacao> subestacaoExistente = subestacaoRepository
					.findByCodigo(rede.getSubestacao().getCodigo());
			if (subestacaoExistente.isPresent()) {
				return Optional.ofNullable(this.redeRepository.save(rede));
			} else {
				Subestacao subestacao = subestacaoRepository.save(rede.getSubestacao());
				rede.setSubestacao(subestacao);
				return Optional.ofNullable(this.redeRepository.save(rede));
			}
		}
	}
	
	

}
