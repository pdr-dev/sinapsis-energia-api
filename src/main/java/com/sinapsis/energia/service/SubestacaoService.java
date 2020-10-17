package com.sinapsis.energia.service;

import java.util.Optional;

import com.sinapsis.energia.model.Subestacao;

/**
 * @author Pedro Henrique
 */
public interface SubestacaoService {

	/**
	 * Retorna uma lista de subestações
	 * 
	 * @return Optional<Subestacao>
	 */
	Optional<Subestacao> listar();

	/**
	 * Retorna uma determinada subestação de acordo com o código
	 * 
	 * @param codigo
	 * @return Optional<Subestacao>
	 */
	Optional<Subestacao> buscarPorCodigo(String codigo);

	/**
	 * Cria uma nova subestação
	 * 
	 * @param Subestacao
	 * @return Subestacao
	 */
	Subestacao incluir(Subestacao subestacao);

	/**
	 * Altera uma subestação existente
	 * 
	 * @param idSubestacao
	 * @param subestacao
	 * @return Optional<Subestacao>
	 */
	Optional<Subestacao> alterar(Long idSubestacao, Subestacao subestacao);

	/**
	 * Exclui uma subestação
	 * 
	 * @param idSubestacao
	 */
	void excluir(Long idSubestacao);

}