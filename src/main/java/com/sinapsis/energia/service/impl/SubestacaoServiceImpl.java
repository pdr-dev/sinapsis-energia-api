package com.sinapsis.energia.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinapsis.energia.exception.SubestacaoExistenteException;
import com.sinapsis.energia.exception.SubestacaoNaoEncontradaException;
import com.sinapsis.energia.model.Subestacao;
import com.sinapsis.energia.repository.SubestacaoRepository;
import com.sinapsis.energia.service.SubestacaoService;

/**
 * @author Pedro Henrique
 */

@Service
public class SubestacaoServiceImpl implements SubestacaoService {

	private SubestacaoRepository subestacaoRepository;

	@Autowired
	public SubestacaoServiceImpl(SubestacaoRepository subestacaoRepository) {
		this.subestacaoRepository = subestacaoRepository;
	}

	@Override
	public Optional<List<Subestacao>> listar() {
		List<Subestacao> subestacoes = this.subestacaoRepository.findAll();

		if (subestacoes.isEmpty())
			return Optional.empty();

		return Optional.ofNullable(subestacoes);
	}

	@Override
	public Optional<Subestacao> buscarPorCodigo(String codigo) {
		Optional<Subestacao> subestacao = this.subestacaoRepository.findByCodigo(codigo);

		if (!subestacao.isPresent())
			throw new SubestacaoNaoEncontradaException();

		return subestacao;
	}

	@Override
	public Optional<Subestacao> incluir(Subestacao subestacao) {
		Optional<Subestacao> subestacaoExistente = this.subestacaoRepository.findByCodigo(subestacao.getCodigo());

		if (subestacaoExistente.isPresent())
			throw new SubestacaoExistenteException("Já existe uma Subestação com esse código!");

		return Optional.ofNullable(this.subestacaoRepository.save(subestacao));
	}

	@Override
	public Optional<Subestacao> alterar(Long idSubestacao, Subestacao subestacao) {
		Optional<Subestacao> subestacaoExistente = this.subestacaoRepository.findById(idSubestacao);

		if (!subestacaoExistente.isPresent())
			throw new SubestacaoNaoEncontradaException();

		subestacao.setIdSubestacao(idSubestacao);
		this.subestacaoRepository.save(subestacao);
		return Optional.ofNullable(subestacao);
	}

	@Override
	public void excluir(Long idSubestacao) {
		this.subestacaoRepository.deleteById(idSubestacao);
	}
}
