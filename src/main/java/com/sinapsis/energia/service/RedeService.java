package com.sinapsis.energia.service;

import java.util.List;
import java.util.Optional;

import com.sinapsis.energia.model.Rede;
import com.sinapsis.energia.model.Subestacao;

/**
 * @author Pedro Henrique
 */
public interface RedeService {

	Optional<List<Rede>> listar();

	Optional<Rede> buscarPorCodigoDaRede(String codigo);

	Optional<List<Rede>> buscarPorSubestacao(Subestacao subestacao);

	Optional<Rede> incluir(Rede rede);

	Optional<Rede> alterar(Long idRede, Rede rede);

	void excluir(Long idRede);
}
