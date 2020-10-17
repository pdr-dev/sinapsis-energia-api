package com.sinapsis.energia.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinapsis.energia.exception.RedeExistenteException;
import com.sinapsis.energia.exception.RedeNaoEncontradaException;
import com.sinapsis.energia.model.Rede;
import com.sinapsis.energia.model.Subestacao;
import com.sinapsis.energia.repository.RedeRepository;
import com.sinapsis.energia.repository.SubestacaoRepository;
import com.sinapsis.energia.service.RedeService;

/**
 * @author Pedro Henrique
 */

@Service
public class RedeServiceImpl implements RedeService {

	private RedeRepository redeRepository;
	private SubestacaoRepository subestacaoRepository;

	@Autowired
	public RedeServiceImpl(RedeRepository redeRepository, SubestacaoRepository subestacaoRepository) {
		this.redeRepository = redeRepository;
		this.subestacaoRepository = subestacaoRepository;
	}

	@Override
	public Optional<List<Rede>> listar() {
		List<Rede> redes = redeRepository.findAll();

		if (redes.isEmpty())
			return Optional.empty();

		return Optional.ofNullable(redes);
	}

	@Override
	public Optional<Rede> buscarPorCodigoDaRede(String codigo) {
		Optional<Rede> rede = redeRepository.findByCodigo(codigo);

		if (!rede.isPresent())
			throw new RedeNaoEncontradaException();

		return rede;
	}

	@Override
	public Optional<List<Rede>> buscarPorSubestacao(Subestacao subestacao) {
		List<Rede> redesPorSubestacao = redeRepository.findBySubestacao(subestacao);

		if (redesPorSubestacao.isEmpty())
			return Optional.empty();

		return Optional.ofNullable(redesPorSubestacao);
	}

	@Override
	public Optional<Rede> incluir(Rede rede) {
		Optional<Rede> redeExistente = this.redeRepository.findByCodigo(rede.getCodigo());

		if (redeExistente.isPresent()) {
			throw new RedeExistenteException("Já existe uma Rede com esse código!");
		} else {
			Optional<Subestacao> subestacaoExistente = subestacaoRepository
					.findByCodigo(rede.getSubestacao().getCodigo());
			if (!subestacaoExistente.isPresent()) {
				Subestacao subestacao = subestacaoRepository.save(rede.getSubestacao());
				rede.setSubestacao(subestacao);
			}
			return Optional.ofNullable(this.redeRepository.save(rede));
		}
	}

	@Override
	public Optional<Rede> alterar(Long idRede, Rede rede) {
		Optional<Rede> redeExistente = redeRepository.findById(idRede);

		if (!redeExistente.isPresent())
			throw new RedeNaoEncontradaException();

		Optional<Subestacao> subestacaoExistente = subestacaoRepository.findByCodigo(rede.getSubestacao().getCodigo());
		if (!subestacaoExistente.isPresent()) {
			Subestacao subestacao = subestacaoRepository.save(rede.getSubestacao());
			rede.setSubestacao(subestacao);
		}
		rede.setIdRedeMT(idRede);
		redeRepository.save(rede);
		return Optional.ofNullable(rede);
	}

	@Override
	public void excluir(Long idRede) {
		this.redeRepository.deleteById(idRede);
	}
}
